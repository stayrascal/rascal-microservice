package com.stayrascal.cloud.order.contract.hystrix;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.order.contract.client.OrderServiceClient;
import com.stayrascal.cloud.order.contract.command.CreateOrderCommand;
import com.stayrascal.cloud.order.contract.command.UpdateOrderCommand;
import com.stayrascal.cloud.order.contract.dto.OrderDto;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderServiceClientHystrix implements OrderServiceClient {
    @Override
    public CreatedResult createOrder(CreateOrderCommand createOrderCommand) {
        return new CreatedResult("0");
    }

    @Override
    public OrderDto getOrder(String orderId) {
        return null;
    }

    @Override
    public PageResult listOrders(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return new PageResult(0L, 0, 0, null);
    }

    @Override
    public ResponseEntity updateOrder(String id, UpdateOrderCommand command) {
        return ResponseEntity.noContent().build();
    }
}
