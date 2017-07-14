package com.stayrascal.cloud.order.domain.mapper;

import com.stayrascal.cloud.common.lib.mapper.DefaultMapper;
import com.stayrascal.cloud.order.domain.entity.Order;
import com.stayrascal.cloud.order.domain.entity.OrderItem;
import com.stayrascal.cloud.order.infrastructure.persistence.po.OrderItemPo;
import com.stayrascal.cloud.order.infrastructure.persistence.po.OrderPo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderPoMapper extends DefaultMapper {
    public OrderPoMapper() {
        register(OrderPo.class, Order.class);
    }

    public Order orderFromPo(OrderPo orderPo) {
        Order order = map(orderPo, Order.class);
        order.setItems(orderPo.getItems().stream().map(this::orderItemFromPo).collect(Collectors.toList()));
        return order;
    }

    private OrderItem orderItemFromPo(OrderItemPo orderItemPo) {
        return map(orderItemPo, OrderItem.class);
    }

    public OrderPo orderToPo(Order order) {
        OrderPo orderPo = map(order, OrderPo.class);
        String orderId = order.getId();
        orderPo.setItems(order.getItems().stream().map(item -> orderItemToPo(orderId, item)).collect(Collectors.toList()));
        return orderPo;
    }

    private OrderItemPo orderItemToPo(String orderId, OrderItem orderItem) {
        return map(orderItem, OrderItemPo.class);
    }
}
