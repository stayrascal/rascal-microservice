package com.stayrascal.cloud.product.resource;

import com.stayrascal.cloud.product.contract.command.CreateProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateProductInfoCommand;
import com.stayrascal.cloud.product.contract.dto.ProductDto;
import com.stayrascal.cloud.product.facade.ProductFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("products")
@Api(value = "product", description = "Access to product resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    private ProductFacade productFacade;

    @Autowired
    public ProductResource(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @Path("/{id}")
    @ApiOperation(value = "Get product by id", response = ProductDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Get product successfully"),
        @ApiResponse(code = 404, message = "No product matches given id")
    })
    @GET
    public Response getProduct(@NotNull @PathParam("id") String productId) {

        ProductDto productDto = productFacade.getProductById(productId);
        return Response.ok().entity(productDto).build();
    }

    @POST
    @ApiOperation(value = "Create product", response = ProductDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Create product successfully")
    })
    public Response createProduct(@NotNull CreateProductCommand createProductCommand) {
        String id = productFacade.createProduct(createProductCommand);

        return Response.created(URI.create("/" + id)).build();
    }

    @PUT
    @Path("{id}")
    @ApiOperation(value = "Update product info", response = ProductDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Update product info successfully"),
        @ApiResponse(code = 404, message = "Could not find product by id")
    })
    public Response updateProductInfo(@NotNull @PathParam("id") String productId,
                                      @NotNull UpdateProductInfoCommand command) {
        productFacade.updateProductInfo(productId, command);

        return Response.ok().build();
    }
}
