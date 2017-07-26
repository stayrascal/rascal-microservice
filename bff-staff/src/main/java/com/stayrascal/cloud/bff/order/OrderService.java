package com.stayrascal.cloud.bff.order;

import com.stayrascal.cloud.bff.common.BatchResponseElement;
import com.stayrascal.cloud.bff.common.PageResponse;
import com.stayrascal.cloud.bff.order.request.BatchUpdateOrderCommand;
import com.stayrascal.cloud.bff.order.response.ListedOrder;
import com.stayrascal.cloud.bff.order.response.OrderCommon;
import com.stayrascal.cloud.bff.order.response.OrderDetail;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.order.contract.client.OrderServiceClient;
import com.stayrascal.cloud.order.contract.client.TransactionServiceClient;
import com.stayrascal.cloud.order.contract.dto.OrderDto;
import com.stayrascal.cloud.order.contract.dto.OrderItemDto;
import com.stayrascal.cloud.order.contract.dto.TransactionDto;
import com.stayrascal.cloud.order.contract.enumeration.OrderStatus;
import com.stayrascal.cloud.product.contract.client.ProductServiceClient;
import com.stayrascal.cloud.product.contract.client.StoreProductServiceClient;
import com.stayrascal.cloud.product.contract.dto.StoreProductDto;
import com.stayrascal.cloud.user.member.contract.client.MemberServiceClient;
import com.stayrascal.cloud.user.member.contract.dto.MemberDto;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderService {
    private final OrderServiceClient orderServiceClient;
    private final MemberServiceClient memberServiceClient;
    private final TransactionServiceClient transactionServiceClient;
    private final StoreProductServiceClient storeProductServiceClient;
    private final DefaultMapper mapper;

    @Autowired
    public OrderService(OrderServiceClient orderServiceClient, MemberServiceClient memberServiceClient,
                        TransactionServiceClient transactionServiceClient, ProductServiceClient productServiceClient,
                        StoreProductServiceClient storeProductServiceClient) {
        this.orderServiceClient = orderServiceClient;
        this.memberServiceClient = memberServiceClient;
        this.transactionServiceClient = transactionServiceClient;
        this.storeProductServiceClient = storeProductServiceClient;

        mapper = DefaultMapper.builder()
                .register(OrderDetail.class, OrderDto.class)
                .build();
    }

    public OrderDetail getOrderDetail(String orderId) {
        OrderDto orderDto = orderServiceClient.getOrder(orderId);

        OrderDetail orderDetail = mapper.map(orderDto, OrderDetail.class);
        convertOrderCommon(orderDto, orderDetail);

        orderDetail.getItems().forEach(item -> {
            StoreProductDto storeProduct = storeProductServiceClient.getStoreProduct(item.getStoreProductId());
            item.setProductThumbnail(storeProduct.getThumbnail());
        });

        return orderDetail;
    }

    private void convertOrderCommon(OrderDto orderDto, OrderCommon orderCommon) {
        MemberDto member = memberServiceClient.getMember(orderDto.getUserId());
        orderCommon.setCustomerMobile(member.getMobile());
        orderCommon.setTotalCost(new BigDecimal(calculateTotalCost(orderDto.getItems())));

        if (!Strings.isNullOrEmpty(orderDto.getTransactionId())) {
            TransactionDto transaction = transactionServiceClient.getTransaction(orderDto.getTransactionId());
            orderCommon.setTransactionProvider(transaction.getProvider());
        }
    }

    private Double calculateTotalCost(List<OrderItemDto> items) {
        return items.stream().map(i -> i.getPrice().doubleValue() * i.getQuantity())
                .reduce(0.0, (lv, rv) -> lv + rv);
    }

    private Integer calculateTotalItems(List<OrderItemDto> items) {
        return items.stream().map(i -> i.getQuantity()).reduce(0, (lv, rv) -> lv + rv);
    }

    public PageResponse listOrdersByPage(Integer pageSize, Integer pageIndex, SortType sortType, String sortBy,
                                         String id,
                                         String storeId, OrderStatus status, Long placedTimeFrom, Long placedTimeTo,
                                         String pickupCode, String userId) {
        // TODO: how to handle sort by customer phone number?

        PageResult pageResult = orderServiceClient.listOrders(sortType, sortBy, pageSize, pageIndex,
                id, storeId, status, placedTimeFrom, placedTimeTo, pickupCode, userId);
        List<OrderDto> orderDtoList = pageResult.getItems();
        List<ListedOrder> orders = mapper.mapList(orderDtoList, ListedOrder.class, (dto, listedOrder) -> {
            convertOrderCommon(dto, listedOrder);
            listedOrder.setTotalItems(calculateTotalItems(dto.getItems()));
            return listedOrder;
        });

        return new PageResponse(pageResult.getTotalCount(), pageResult.getPageSize(), pageResult.getPageIndex(),
                orders);
    }

    public List<BatchResponseElement> updateOrders(BatchUpdateOrderCommand command) {
        return command.getCommands().stream().map(cmd -> {
            try {
                orderServiceClient.updateOrder(cmd.getOrderId(), cmd);
                return new BatchResponseElement(false, cmd, null);
            } catch (Exception e) {
                return new BatchResponseElement(true, cmd, e.getMessage());
            }
        }).collect(Collectors.toList());
    }
}
