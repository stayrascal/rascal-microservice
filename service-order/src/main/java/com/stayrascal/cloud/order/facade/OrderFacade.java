package com.stayrascal.cloud.order.facade;

import com.google.common.base.Strings;
import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;
import com.stayrascal.cloud.common.util.TimeRange;
import com.stayrascal.cloud.order.contract.command.CreateOrderCommand;
import com.stayrascal.cloud.order.contract.command.UpdateOrderCommand;
import com.stayrascal.cloud.order.contract.dto.OrderDto;
import com.stayrascal.cloud.order.contract.enumeration.OrderStatus;
import com.stayrascal.cloud.order.domain.entity.Order;
import com.stayrascal.cloud.order.domain.factory.OrderFactory;
import com.stayrascal.cloud.order.domain.mapper.OrderDtoMapper;
import com.stayrascal.cloud.order.domain.repository.OrderRepository;
import com.stayrascal.cloud.order.service.PickupCodeGeneratorService;
import com.stayrascal.clould.common.contract.query.SortQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class OrderFacade {
    public static final Logger LOGGER = LoggerFactory.getLogger(OrderFacade.class);

    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;
    private final OrderDtoMapper orderDtoMapper;
    private final PickupCodeGeneratorService pickupCodeGeneratorService;

    @Autowired
    public OrderFacade(OrderFactory orderFactory, OrderRepository orderRepository, OrderDtoMapper orderDtoMapper, PickupCodeGeneratorService pickupCodeGeneratorService) {
        this.orderFactory = orderFactory;
        this.orderRepository = orderRepository;
        this.orderDtoMapper = orderDtoMapper;
        this.pickupCodeGeneratorService = pickupCodeGeneratorService;
    }

    public OrderDto getOrder(String orderId) {
        return orderDtoMapper.orderToDto(getOrderById(orderId));
    }

    public String createOrder(CreateOrderCommand createOrderCommand) {
        Order order = orderFactory.createOrder(createOrderCommand);
        return orderRepository.insert(order);
    }

    public OrderDto updateOrder(String orderId, UpdateOrderCommand updateOrderCommand) {
        Order order = getOrderById(orderId);
        if (!Strings.isNullOrEmpty(updateOrderCommand.getNote())) {
            order.setNote(updateOrderCommand.getNote());
        }
        OrderStatus newStatus = updateOrderCommand.getStatus();
        if (newStatus != null) {
            order.updateStatus(newStatus);
        }
        orderRepository.update(order);
        return orderDtoMapper.orderToDto(order);
    }

    public long countOrders(String id, String storeId, OrderStatus status, String pickupCode, TimeRange placeTimeRange, String userId) {
        return orderRepository.countOrders(id, storeId, status, pickupCode, placeTimeRange, userId);
    }

    public void finishTransaction(String orderId, String transactionId) {
        final Order order = getOrderById(orderId);
        order.transactionFinished(transactionId, pickupCodeGeneratorService);
        orderRepository.update(order);
    }

    public List<OrderDto> listOrders(SortQuery sortQuery, String id, String storeId, OrderStatus status,
                                     String pickupCode, TimeRange placedTimeRange, String userId) {
        List<Order> orders = orderRepository.listOrders(sortQuery, id, storeId, status, pickupCode, placedTimeRange, userId);
        return orders.stream().map(orderDtoMapper::orderToDto).collect(Collectors.toList());
    }

    Order getOrderById(String orderId) {
        Optional<Order> order = orderRepository.ofId(orderId);
        if (!order.isPresent()) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR, "Order not found of id {}", orderId);
        }
        return order.get();
    }
}
