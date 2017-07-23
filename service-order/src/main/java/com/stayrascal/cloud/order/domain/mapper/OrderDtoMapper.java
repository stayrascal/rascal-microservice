package com.stayrascal.cloud.order.domain.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.order.contract.dto.OrderDto;
import com.stayrascal.cloud.order.contract.dto.OrderItemDto;
import com.stayrascal.cloud.order.domain.entity.Order;
import com.stayrascal.cloud.order.domain.entity.OrderItem;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderDtoMapper extends DefaultMapper {
    public OrderDtoMapper() {
        register(OrderDto.class, Order.class);
        register(OrderItemDto.class, OrderItem.class);
    }

    public Order orderFromDto(OrderDto orderDto) {
        Order order = map(orderDto, Order.class);
        order.setItems(orderDto.getItems().stream().map(this::orderItemFromDto).collect(Collectors.toList()));
        return order;
    }

    private OrderItem orderItemFromDto(OrderItemDto orderItemDto) {
        return map(orderItemDto, OrderItem.class);
    }

    public OrderDto orderToDto(Order order) {
        OrderDto orderDto = map(order, OrderDto.class);
        orderDto.setItems(order.getItems().stream().map(this::orderItemToDto).collect(Collectors.toList()));
        return orderDto;
    }

    private OrderItemDto orderItemToDto(OrderItem orderItem) {
        return map(orderItem, OrderItemDto.class);
    }
}
