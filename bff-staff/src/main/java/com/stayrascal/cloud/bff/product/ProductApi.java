package com.stayrascal.cloud.bff.product;

import static com.stayrascal.cloud.common.jersey.JerseyHelpers.uriOfCreated;

import com.stayrascal.cloud.common.contract.QueryMap;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.product.contract.client.ProductServiceClient;
import com.stayrascal.cloud.product.contract.command.CreateProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateProductCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("products")
@Api(value = "product", description = "Access to product resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductApi {
    private final ProductServiceClient productServiceClient;
    private final ProductService productService;

    @Autowired
    public ProductApi(ProductServiceClient productServiceClient, ProductService productService) {
        this.productServiceClient = productServiceClient;
        this.productService = productService;
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Get product")
    public Response getProduct(@PathParam("id") String id) {
        return Response.ok().entity(productService.getProduct(id)).build();
    }

    @POST
    @ApiOperation("Create product")
    public Response createProduct(CreateProductCommand command, @Context UriInfo uriInfo) {
        CreatedResult result = productServiceClient.createProduct(command);
        return Response.created(uriOfCreated(uriInfo, result.getId())).entity(result).build();
    }

    @GET
    @Path("catalog-list")
    @ApiOperation("List product for manager")
    public Response listProductForManager(@QueryParam("store_id") String storeId,
                                          @QueryParam("category_id") String categoryId,
                                          @QueryParam("create_time_from") Long createTimeFrom,
                                          @QueryParam("create_time_to") Long createTimeTo,
                                          @QueryParam("name") String name,
                                          @QueryParam("filter_included") Boolean filterIncluded,
                                          @QueryParam("sort_type") SortType sortType,
                                          @QueryParam("sort_by") String sortBy,
                                          @QueryParam("page_size") Integer pageSize,
                                          @QueryParam("page_index") Integer pageIndex,
                                          @Context ContainerRequestContext context) {
//        checkPermission(context, storeId);
        return Response.ok().entity(productServiceClient.listProductCatalogs(sortType, sortBy, pageSize, pageIndex,
                storeId, categoryId, createTimeFrom, createTimeTo, name, filterIncluded))
                .build();
    }

    @GET
    @ApiOperation("List product")
    public Response listProduct(@QueryParam("sort_type") SortType sortType,
                                @QueryParam("sort_by") String sortBy,
                                @QueryParam("page_size") Integer pageSize,
                                @QueryParam("page_index") Integer pageIndex,
                                @QueryParam("time_created_from") String createTimeFrom,
                                @QueryParam("time_created_to") String createTimeTo,
                                @QueryParam("category_id") String categoryId,
                                @QueryParam("name") String name) {
        Map<String, String> queryMap = QueryMap.builder()
                .put("name", name)
                .put("time_created_from", createTimeFrom)
                .put("time_created_to", createTimeTo)
                .put("category_id", categoryId)
                .build();
        return Response.ok()
                .entity(productServiceClient.listProducts(sortType, sortBy, pageSize, pageIndex, queryMap))
                .build();
    }

    @PUT
    @Path("/{id}")
    @ApiOperation("Update product")
    public Response updateProduct(@PathParam("id") String id, UpdateProductCommand command) {
        productServiceClient.updateProduct(id, command);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @ApiOperation("Delete product")
    public Response deleteProduct(@PathParam("id") String id) {
        productServiceClient.deleteProduct(id);
        return Response.ok().build();
    }
}
