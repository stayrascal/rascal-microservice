package com.stayrascal.cloud.order.contract.client;

import com.stayrascal.cloud.common.contract.QueryMap;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.order.contract.command.CreateOrderCommand;
import com.stayrascal.cloud.order.contract.command.UpdateOrderCommand;
import com.stayrascal.cloud.order.contract.dto.OrderDto;
import com.stayrascal.cloud.order.contract.enumeration.OrderStatus;
import com.stayrascal.cloud.order.contract.hystrix.OrderServiceClientHystrix;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import javax.ws.rs.core.MediaType;

@FeignClient(value = "service-order", fallback = OrderServiceClientHystrix.class)
public interface OrderServiceClient {
    @RequestMapping(method = RequestMethod.POST, path = "/rest/orders", consumes = MediaType.APPLICATION_JSON)
    CreatedResult createOrder(CreateOrderCommand createOrderCommand);

    @RequestMapping(method = {RequestMethod.GET}, value = {"/rest/orders/{id}"}, produces = MediaType.APPLICATION_JSON)
    OrderDto getOrder(@PathVariable("id") String orderId);

    @RequestMapping(method = RequestMethod.GET, value = "/rest/orders", produces = MediaType.APPLICATION_JSON)
    PageResult listOrders(@RequestParam("sort_type") SortType sortType,
                          @RequestParam("sort_by") String sortBy,
                          @RequestParam("page_size") Integer pageSize,
                          @RequestParam("page_index") Integer pageIndex,
                          @RequestParam Map<String, String> queryMap);

    default PageResult listOrders(Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return listOrders(SortType.ASC, "timeCreated", pageSize, pageIndex, queryMap);
    }

    default PageResult listOrders(SortType sortType,
                                  String sortBy,
                                  Integer pageSize,
                                  Integer pageIndex,

                                  @RequestParam("id") String id,
                                  @RequestParam("store_id") String storeId,
                                  @RequestParam("status") OrderStatus status,
                                  @RequestParam("placed_time_from") Long placedTimeFrom,
                                  @RequestParam("placed_time_to") Long placedTimeTo,
                                  @RequestParam("pickup_code") String pickupCode,
                                  @RequestParam("user_id") String userId) {
        Map<String, String> queryMap = QueryMap.builder()
                .put("id", id)
                .put("store_id", storeId)
                .put("status", status, Enum::name)
                .put("placed_time_from", placedTimeFrom, String::valueOf)
                .put("placed_time_to", placedTimeTo, String::valueOf)
                .put("pickup_code", pickupCode)
                .put("user_id", userId).build();

        return listOrders(sortType, sortBy, pageSize, pageIndex, queryMap);
    }


    @RequestMapping(method = {RequestMethod.PUT}, value = {"/rest/orders/{id}"}, produces = MediaType.APPLICATION_JSON)
    ResponseEntity updateOrder(@PathVariable("id") String id, UpdateOrderCommand command);

}
