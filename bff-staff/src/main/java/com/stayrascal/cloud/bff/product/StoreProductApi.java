package com.stayrascal.cloud.bff.product;

import static com.stayrascal.cloud.common.jersey.JerseyHelpers.uriOfCreated;

import com.stayrascal.cloud.bff.common.BatchResponseElement;
import com.stayrascal.cloud.bff.common.DefaultParams;
import com.stayrascal.cloud.bff.common.ListResponse;
import com.stayrascal.cloud.bff.product.request.BatchCreateStoreProductCommand;
import com.stayrascal.cloud.bff.product.request.BatchUpdateStoreProductCommand;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.product.contract.client.StoreProductServiceClient;
import com.stayrascal.cloud.product.contract.command.CreateStoreProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateStoreItemCommand;
import com.stayrascal.cloud.product.contract.command.UpdateStoreProductCommand;
import com.stayrascal.cloud.product.contract.dto.StoreProductDto;
import com.stayrascal.cloud.product.contract.enumeration.StoreProductStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@Path("store-products")
@Api(value = "store product", description = "Access to store product resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoreProductApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreProductApi.class);

    private final StoreProductService storeProductService;
    private final StoreProductServiceClient storeProductServiceClient;

    @Autowired
    public StoreProductApi(StoreProductService storeProductService,
                           StoreProductServiceClient storeProductServiceClient) {
        this.storeProductService = storeProductService;
        this.storeProductServiceClient = storeProductServiceClient;
    }

    @POST
    @ApiOperation("Create single store product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create successfully", response = CreatedResult.class),
    })
    public Response createStoreProduct(CreateStoreProductCommand command,
                                       @Context ContainerRequestContext context,
                                       @Context UriInfo uriInfo) {
//        checkPermission(context, command.getStoreId());

        CreatedResult idResult = storeProductServiceClient.createStoreProduct(command);
        return Response.created(uriOfCreated(uriInfo, idResult.getId())).entity(idResult).build();
    }

    @POST
    @Path("batch")
    @ApiOperation("Batch create store products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Batch run successfully", response = ListResponse.class),
    })
    public Response batchCreateStoreProduct(BatchCreateStoreProductCommand batch,
                                            @Context ContainerRequestContext context) {

//        batch.getCommands().forEach(c -> checkPermission(context, c.getStoreId()));
        List<BatchResponseElement> responseElements = storeProductService.batchCreateStoreProduct(batch);
        return Response.ok().entity(new ListResponse(responseElements)).build();
    }

    @GET
    @Path("{id}")
    @ApiOperation("Get store product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get store product successfully", response = StoreProductDto.class),
    })
    public Response getStoreProduct(@PathParam("id") String id, @Context ContainerRequestContext context) {
        final StoreProductDto result = storeProductServiceClient.getStoreProduct(id);
//        checkPermission(context, result.getStoreId());
        return Response.ok().entity(result).build();
    }

    @GET
    @ApiOperation("List store products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List store product successfully", response = PageResult.class),
    })
    public Response listStoreProduct(@QueryParam("category_id") String categoryId,
                                     @QueryParam("store_id") String storeId,
                                     @QueryParam("create_time_from") Long createTimeFrom,
                                     @QueryParam("create_time_to") Long createTimeTo,
                                     @QueryParam("name") String name,
                                     @QueryParam("status") StoreProductStatus status,
                                     @QueryParam("sort_type") @DefaultValue(DefaultParams.SORT_TYPE) SortType sortType,
                                     @QueryParam("sort_by") @DefaultValue(DefaultParams.SORT_BY) String sortBy,
                                     @QueryParam("page_size") @DefaultValue(DefaultParams.PAGE_SIZE) Integer pageSize,
                                     @QueryParam("page_index") @DefaultValue(DefaultParams.PAGE_INDEX) Integer pageIndex,
                                     @Context ContainerRequestContext context) {
//        checkPermission(context, storeId);
        sortBy = Optional.ofNullable(sortBy).orElse(DefaultParams.SORT_BY);
        return Response.ok()
                .entity(storeProductService.listProductByStore(categoryId, storeId, createTimeFrom, createTimeTo,
                        name, status, sortType, sortBy, pageSize, pageIndex))
                .build();
    }

    @PUT
    @Path("{id}/items/{item_id}")
    @ApiOperation("update store item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update store product successfully"),
    })
    public Response updateStoreItemQuantity(@PathParam("id") String storeProductId,
                                            @PathParam("item_id") String storeItemId,
                                            UpdateStoreItemCommand command,
                                            @Context ContainerRequestContext context) {
//        final StoreProductDto storeProduct = storeProductServiceClient.getStoreProduct(storeProductId);
//        checkPermission(context, storeProduct.getStoreId());
        storeProductServiceClient.updateStoreProductItem(storeProductId, storeItemId, command);

        return Response.ok().build();
    }

    @PUT
    @Path("batch")
    @ApiOperation("Batch update store product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Batch update store product successfully"),
    })
    public Response batchUpdateStoreProduct(BatchUpdateStoreProductCommand batch,
                                            @Context ContainerRequestContext context) {
        List<BatchResponseElement> responseElements = storeProductService.batchUpdateStoreProduct(batch);
        return Response.ok().entity(new ListResponse(responseElements)).build();
    }

    @PUT
    @Path("{id}")
    @ApiOperation("update store product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update store product successfully"),
    })
    public Response updateStoreProduct(@PathParam("id") String storeProductId,
                                       UpdateStoreProductCommand command) {
        storeProductServiceClient.updateStoreProduct(storeProductId, command);

        return Response.ok().build();
    }
}
