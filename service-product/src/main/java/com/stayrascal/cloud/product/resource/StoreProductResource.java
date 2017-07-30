package com.stayrascal.cloud.product.resource;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.product.contract.command.CreateStoreProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateStoreProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateStoreProductItemCommand;
import com.stayrascal.cloud.product.contract.dto.ProductDto;
import com.stayrascal.cloud.product.contract.dto.StoreProductDto;
import com.stayrascal.cloud.product.facade.StoreProductFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("store-products")
@Api(value = "product", description = "Access to product resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoreProductResource {

    @Autowired
    private StoreProductFacade facade;

    @GET
    @Path("/{id}")
    @ApiOperation(value = "Get product by id", response = ProductDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get product successfully"),
            @ApiResponse(code = 404, message = "No product matches given id")
    })
    public Response getStoreProduct(@NotNull @PathParam("id") String id) {

        StoreProductDto storeProduct = facade.getStoreProductById(id);
        return Response.ok().entity(storeProduct).build();
    }

    /*@GET
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = PageResult.class, message = "List Store Products successfully")
    })
    public PageResult listStoreProductByCategory(@RequestParam("sort_type") SortType sortType, @RequestParam("sort_by") String sortBy,
                                                 @RequestParam("page_size") Integer pageSize, @RequestParam("page_index") Integer pageIndex,
                                                 @RequestParam Map<String, String> queryMaps) {
        SortQuery sortQuery = new SortQuery(sortType, sortBy, pageSize, pageIndex);
        List<StoreProductDto> storeProductDtos = facade.listStoreProducts(sortQuery, queryMaps);
        return new PageResult((long) storeProductDtos.size(), pageSize, pageIndex, storeProductDtos);
    }*/

    @GET
    @Path("/{store_id}/categories/{category_id}/products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = PageResult.class, message = "List Store Products successfully")
    })
    public PageResult listStoreProductsByCategory(@NotNull @PathParam("store_id") String storeId,
                                                  @NotNull @PathParam("category_id") String categoryId,
                                                  @QueryParam("sort_type") SortType sortType, @QueryParam("sort_by") String sortBy,
                                                  @QueryParam("page_size") Integer pageSize, @QueryParam("page_index") Integer pageIndex,
                                                  @RequestParam Map<String, String> queryMaps) {
        SortQuery sortQuery = new SortQuery(sortType, sortBy, pageSize, pageIndex);
        queryMaps.put("store_id", storeId);
        queryMaps.put("category_id", categoryId);
        List<StoreProductDto> storeProductDtos = facade.listStoreProducts(sortQuery, queryMaps);
        return new PageResult((long) storeProductDtos.size(), pageSize, pageIndex, storeProductDtos);
    }

    @POST
    @ApiOperation(value = "Create Store Product", response = ProductDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create store product successfully")
    })
    public Response createProduct(@NotNull CreateStoreProductCommand command) {
        String id = facade.createStoreProduct(command);

        return Response.created(URI.create("/" + id)).build();
    }

    @PUT
    @Path("/{id}")
    @ApiOperation(value = "Update store product info", response = ProductDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update store product info successfully"),
            @ApiResponse(code = 404, message = "Could not find store product by id")
    })
    public StoreProductDto updateStoreProduct(@NotNull @PathParam("id") String id,
                                              @NotNull UpdateStoreProductCommand command) {
        return facade.updateStoreProduct(id, command);
    }

    @PUT
    @Path("/{id}/items/{item_id}")
    @ApiOperation(value = "Update store product info", response = ProductDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update store product info successfully"),
            @ApiResponse(code = 404, message = "Could not find store product by id")
    })
    public StoreProductDto updateStoreProductItem(@NotNull @PathParam("id") String id,
                                                  @NotNull @PathParam("item_id") String itemId,
                                                  @NotNull UpdateStoreProductItemCommand command) {
        return facade.updateStoreProductItem(id, itemId, command);
    }
}
