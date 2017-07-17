package com.stayrascal.cloud.order.domain.factory;

import com.google.common.base.Strings;
import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.jersey.exception.BadRequestException;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;
import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.order.contract.command.CreateOrderCommand;
import com.stayrascal.cloud.order.contract.command.CreateOrderCommand.CreateOrderItemCommand;
import com.stayrascal.cloud.order.contract.enumeration.OrderStatus;
import com.stayrascal.cloud.order.domain.entity.Order;
import com.stayrascal.cloud.order.domain.entity.OrderItem;
import com.stayrascal.cloud.order.external.adapter.StoreProductAdapter;
import com.stayrascal.cloud.order.external.entity.StoreItem;
import com.stayrascal.cloud.order.external.entity.StoreProduct;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderFactory.class);

    private final UniqueKeyGenerator keyGenerator;
    private final StoreProductAdapter storeProductAdapter;
    private final DefaultMapper commandMapper;

    @Autowired
    public OrderFactory(UniqueKeyGenerator keyGenerator, StoreProductAdapter storeProductAdapter) {
        this.keyGenerator = keyGenerator;
        this.storeProductAdapter = storeProductAdapter;
        this.commandMapper = DefaultMapper.builder()
                .register(CreateOrderCommand.class, Order.class)
                .register(CreateOrderItemCommand.class, OrderItem.class)
                .build();
    }

    public Order createOrder(CreateOrderCommand command) {
        Order order = commandMapper.map(command, Order.class);
        order.setItems(
                command.getItems().stream().map(i -> commandMapper.map(i, OrderItem.class)).collect(Collectors.toList())
        );
        return complementCreatedOrder(order);
    }

    private Order complementCreatedOrder(Order order) {
        order.setId(keyGenerator.generateKey());
        order.setTimeCreated(DateTime.now().toDate());
        order.setStatus(OrderStatus.OPEN);

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "Create order without order items!");
        }

        if (Strings.isNullOrEmpty(order.getStoreId())) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "No store id provided: {}", order);
        }

        order.getItems().forEach(this::complementOrderItem);

        return order;
    }

    private void complementOrderItem(OrderItem orderItem) {
        validateOrderItem(orderItem);

        orderItem.setId(null);

        StoreProduct storeProduct = storeProductAdapter.getStoreProduct(orderItem.getStoreProductId());
        StoreItem storeItem = storeProduct.getItems().stream().filter(item -> item.getId().equals(orderItem
                .getStoreItemId())).findFirst().orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR,
                "No matching store item with id {}, of store product with id {}",
                orderItem.getStoreItemId(),
                orderItem.getStoreProductId()));

        if (orderItem.getQuantity() > storeItem.getQuantity()) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "No enough stock for store item {}", storeItem.getId());
        }

        if (!orderItem.getPrice().equals(storeItem.getPrice())) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "Price not matching for store item {}, order item price is {}, given price is {}",
                    storeItem.getId(), orderItem.getPrice(), orderItem.getPrice());
        }

        orderItem.setStoreProductName(storeProduct.getName());
        orderItem.setStoreItemName(formStoreItemName(storeItem));
    }

    private String formStoreItemName(StoreItem storeItem) {
        return storeItem.getOptionPairs().stream().map(op -> op.getName()).collect(Collectors
                .joining("，"));
    }

    private void validateOrderItem(OrderItem orderItem) {
        if (orderItem.getPrice() == null) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "Order item doesn't have price", orderItem.getId());
        }

        if (!Strings.isNullOrEmpty(orderItem.getStoreProductName())) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "Order item already contain product item name: {}", orderItem.getStoreProductName());
        }

        if (orderItem.getQuantity() == null || orderItem.getQuantity() <= 0) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "Order item doesn't have quantity or quantity is illegal: {}", orderItem.getQuantity());
        }

        if (orderItem.getStoreItemId() == null || orderItem.getStoreProductId() == null) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "Order item doesn't have store product and item IDs", orderItem);
        }
    }
}
