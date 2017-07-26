package com.stayrascal.cloud.bff.order;


import com.stayrascal.cloud.bff.common.BatchResponseElement;
import com.stayrascal.cloud.bff.common.ListResponse;
import com.stayrascal.cloud.bff.common.PageResponse;
import com.stayrascal.cloud.bff.order.request.BatchUpdateOrderCommand;
import com.stayrascal.cloud.bff.order.response.OrderDetail;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.order.contract.enumeration.OrderStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("orders")
@Api(value = "orders", description = "Order related apis")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderApi {
    private final OrderService orderService;

    @Autowired
    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }

    @GET
    @Path("{id}")
    @ApiOperation(value = "Get order details for management page")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get order successfully", response = OrderDetail.class),
            @ApiResponse(code = 404, message = "No order matches given id")
    })
    public Response getOrder(@PathParam("id") String orderId) {
        OrderDetail orderDetail = orderService.getOrderDetail(orderId);

        return Response.ok().entity(orderDetail).build();
    }

    @GET
    @ApiOperation(value = "List orders for management page", response = PageResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List orders successfully")
    })
    public Response listOrders(@NotNull @QueryParam("page_size") Integer pageSize,
                               @NotNull @QueryParam("page_index") Integer pageIndex,
                               @NotNull @QueryParam("sort_type") SortType sortType,
                               @NotNull @QueryParam("sort_by") String sortBy,
                               @QueryParam("id") String id,
                               @QueryParam("store_id") String storeId,
                               @QueryParam("status") OrderStatus status,
                               @QueryParam("placed_time_from") Long placedTimeFrom,
                               @QueryParam("placed_time_to") Long placedTimeTo,
                               @QueryParam("pickup_code") String pickupCode,
                               @QueryParam("user_id") String userId) {
        final PageResponse pageResponse = orderService.listOrdersByPage(pageSize, pageIndex, sortType, sortBy,
                id, storeId, status, placedTimeFrom, placedTimeTo, pickupCode, userId);
        return Response.ok().entity(pageResponse).build();
    }

    @POST
    @Path("batch")
    @ApiOperation(value = "Update orders for management page")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update orders successfully", response = ListResponse.class)
    })
    public Response updateOrders(BatchUpdateOrderCommand command) {
        final List<BatchResponseElement> results = orderService.updateOrders(command);
        return Response.ok().entity(new ListResponse(results)).build();
    }
}

