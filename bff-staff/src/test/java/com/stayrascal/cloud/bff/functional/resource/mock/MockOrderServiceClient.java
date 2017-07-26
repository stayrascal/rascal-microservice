package com.stayrascal.cloud.bff.functional.resource.mock;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.order.contract.client.OrderServiceClient;
import com.stayrascal.cloud.order.contract.command.CreateOrderCommand;
import com.stayrascal.cloud.order.contract.command.UpdateOrderCommand;
import com.stayrascal.cloud.order.contract.dto.OrderDto;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Component
public class MockOrderServiceClient implements OrderServiceClient {

    private String orderJson = "";

    private String orderListJson = "";

    @Override
    public CreatedResult createOrder(CreateOrderCommand createOrderCommand) {
        return null;
    }

    @Override
    public OrderDto getOrder(String orderId) {
        return MockUtils.fromJson(orderJson, OrderDto.class);
    }

    @Override
    public PageResult listOrders(@RequestParam("sort_type") SortType sortType,
                                 @RequestParam("sort_by") String sortBy,
                                 @RequestParam("page_size") Integer pageSize,
                                 @RequestParam("page_index") Integer pageIndex,
                                 @RequestParam Map<String, String> queryMap) {
        return MockUtils.fromJson(orderListJson, PageResult.class);
    }

    @Override
    public ResponseEntity updateOrder(String id, UpdateOrderCommand command) {
        return null;
    }

    public void setOrderJson(String orderJson) {
        this.orderJson = orderJson;
    }

    public void setOrderListJson(String orderListJson) {
        this.orderListJson = orderListJson;
    }
}
