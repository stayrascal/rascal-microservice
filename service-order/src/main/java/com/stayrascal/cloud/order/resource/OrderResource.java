package com.stayrascal.cloud.order.resource;

import static com.stayrascal.cloud.common.jersey.JerseyHelpers.uriOfCreated;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.common.util.TimeRange;
import com.stayrascal.cloud.order.contract.command.CreateOrderCommand;
import com.stayrascal.cloud.order.contract.command.UpdateOrderCommand;
import com.stayrascal.cloud.order.contract.dto.OrderDto;
import com.stayrascal.cloud.order.contract.enumeration.OrderStatus;
import com.stayrascal.cloud.order.facade.OrderFacade;

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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Component
@Path("orders")
@Api(value = "order", description = "Access to order resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
    private OrderFacade orderFacade;

    @Autowired
    public OrderResource(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @Path("/{id}")
    @ApiOperation(value = "Get order by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get order successfully", response = OrderDto.class),
            @ApiResponse(code = 404, message = "No order matches given id")
    })
    @GET
    public OrderDto getOrder(@NotNull @PathParam("id") String orderId) {
        return orderFacade.getOrder(orderId);
    }


    @ApiOperation(value = "List orders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List orders successfully", response = PageResult.class)
    })
    @GET
    public PageResult listOrders(@NotNull @QueryParam("page_size") Integer pageSize,
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
        SortQuery sortQuery = new SortQuery(sortType, sortBy, pageSize, pageIndex);
        TimeRange placedTimeRange = TimeRange.from(placedTimeFrom, placedTimeTo);
        long totalCount = orderFacade.countOrders(id, storeId, status, pickupCode, placedTimeRange, userId);

        List<OrderDto> orders = orderFacade.listOrders(sortQuery, id, storeId, status, pickupCode, placedTimeRange, userId);
        return new PageResult(totalCount, pageSize, pageIndex, orders);
    }

    @POST
    @ApiOperation(value = "Create Order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create order successfully", response = CreatedResult.class)
    })
    public Response createOrder(@NotNull CreateOrderCommand createOrderCommand, @Context UriInfo uriInfo) {
        String id = orderFacade.createOrder(createOrderCommand);
        return Response.created(uriOfCreated(uriInfo, id)).entity(new CreatedResult(id)).build();
    }

    @PUT
    @Path("{id}")
    @ApiOperation(value = "Update order info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update order successfully", response = OrderDto.class),
            @ApiResponse(code = 404, message = "Could not find order by id")
    })
    public OrderDto updateOrder(@NotNull @PathParam("id") String orderId,
                                @NotNull UpdateOrderCommand command) {
        return orderFacade.updateOrder(orderId, command);
    }

}
