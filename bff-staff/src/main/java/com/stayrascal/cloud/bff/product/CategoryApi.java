package com.stayrascal.cloud.bff.product;

import static com.stayrascal.cloud.common.jersey.JerseyHelpers.uriOfCreated;

import com.stayrascal.cloud.common.contract.QueryMap;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.product.contract.client.CategoryServiceClient;
import com.stayrascal.cloud.product.contract.command.CreateCategoryCommand;
import com.stayrascal.cloud.product.contract.command.CreateProductOptionCommand;
import com.stayrascal.cloud.product.contract.command.UpdateCategoryCommand;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@Path("categories")
@Api(value = "category", description = "Access to category resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryApi {

    @Autowired
    private CategoryServiceClient categoryServiceClient;

    @POST
    @Path("create")
    public Response createCategory(CreateCategoryCommand createCategoriesCommand,
                                   @Context UriInfo uriInfo) {
        final CreatedResult idResult = categoryServiceClient.createCategory(createCategoriesCommand);
        return Response.created(uriOfCreated(uriInfo, idResult.getId())).entity(idResult).build();
    }

    @GET
    @Path("get/{id}")
    public Response getCategory(@PathParam("id") String id) {
        return Response.ok().entity(categoryServiceClient.getCategory(id)).build();
    }

    @GET
    @Path("list/all")
    public Response listCategory(@QueryParam("sort_type") SortType sortType,
                                 @QueryParam("sort_by") String sortBy,
                                 @QueryParam("page_size") Integer pageSize,
                                 @QueryParam("page_index") Integer pageIndex,
                                 @QueryParam("name") String name) {
        if (pageIndex == null) {
            pageIndex = 0;
        }
        if (pageSize == null) {
            pageSize = Integer.MAX_VALUE;
        }
        Map<String, String> queryMap = QueryMap.builder().put("name", name).build();

        return Response.ok().entity(categoryServiceClient.listCategories(sortType, sortBy,
                pageSize, pageIndex, queryMap)).build();
    }

    @PUT
    @Path("update/{id}")
    public Response updateCategory(@PathParam("id") String id, UpdateCategoryCommand updateCategoryCommand) {
        categoryServiceClient.updateCategory(id, updateCategoryCommand);
        return Response.ok().build();
    }

    @POST
    @Path("{id}/options/create")
    public Response createOption(@NotNull @PathParam("id") String categoryId,
                                 @NotNull CreateProductOptionCommand createProductOptionCommand) {
        final CreatedResult result = categoryServiceClient.createOptions(categoryId, createProductOptionCommand);
        return Response.ok().entity(result).build();
    }

}
