package com.stayrascal.cloud.product.resource;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.product.contract.command.CreateCategoryCommand;
import com.stayrascal.cloud.product.contract.command.CreateProductOptionCommand;
import com.stayrascal.cloud.product.contract.command.UpdateCategoryCommand;
import com.stayrascal.cloud.product.contract.dto.CategoryDto;
import com.stayrascal.cloud.product.contract.dto.ProductOptionDto;
import com.stayrascal.cloud.product.facade.CategoryFacade;

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
@Path("categories")
@Api(value = "product", description = "Access to category resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {
    @Autowired
    private CategoryFacade categoryFacade;

    @GET
    @Path("/{id}")
    @ApiOperation(value = "Get category by id", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get category successfully"),
            @ApiResponse(code = 404, message = "No category matches given id")
    })
    public Response getCategory(@NotNull @PathParam("id") String categoryId) {
        CategoryDto category = categoryFacade.getCategoryById(categoryId);
        return Response.ok().entity(category).build();
    }

    @GET
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = PageResult.class, message = "List Categories successfully")
    })
    public PageResult listCategories(@QueryParam("sort_type") SortType sortType, @QueryParam("sort_by") String sortBy,
                                     @QueryParam("page_size") Integer pageSize, @QueryParam("page_index") Integer pageIndex,
                                     @RequestParam Map<String, String> queryMaps) {
        SortQuery sortQuery = new SortQuery(sortType, sortBy, pageSize, pageIndex);
        List<CategoryDto> categoryDtos = categoryFacade.listCategories(sortQuery, queryMaps);
        return new PageResult((long) categoryDtos.size(), pageSize, pageIndex, categoryDtos);
    }

    @POST
    @ApiOperation(value = "Create category", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create category successfully")
    })
    public Response createCategory(@NotNull CreateCategoryCommand createCategoryCommand) {
        String id = categoryFacade.createCategory(createCategoryCommand);

        return Response.created(URI.create("/" + id)).build();
    }

    @PUT
    @Path("/{id}")
    @ApiOperation(value = "Update category info", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update category info successfully"),
            @ApiResponse(code = 404, message = "Could not find category by id")
    })
    public CategoryDto updateProduct(@NotNull @PathParam("id") String categoryId,
                                     @NotNull UpdateCategoryCommand command) {
        return categoryFacade.updateCategory(categoryId, command);
    }

    @POST
    @Path("/{id}/options")
    @ApiOperation(value = "Create Product Option", response = ProductOptionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create category successfully")
    })
    public Response createOptions(@NotNull @PathParam("id") String categoryId,
                                  @NotNull CreateProductOptionCommand command) {
        String id = categoryFacade.createProductOption(categoryId, command);

        return Response.created(URI.create("/" + id)).build();
    }

}
