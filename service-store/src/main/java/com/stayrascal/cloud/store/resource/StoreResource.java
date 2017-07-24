package com.stayrascal.cloud.store.resource;

import com.stayrascal.cloud.store.contract.command.CreateStoreCommand;
import com.stayrascal.cloud.store.contract.command.UpdateStoreCommand;
import com.stayrascal.cloud.store.contract.dto.StoreDto;
import com.stayrascal.cloud.store.facade.StoreFacade;

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
@Path("stores")
@Api(value = "store", description = "Access to store resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoreResource {
    private StoreFacade storeFacade;

    @Autowired
    public StoreResource(StoreFacade storeFacade) {
        this.storeFacade = storeFacade;
    }

    @Path("/{id}")
    @ApiOperation(value = "Get store by id", response = StoreDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get store successfully"),
            @ApiResponse(code = 404, message = "No store matches given id")
    })
    @GET
    public Response getStore(@NotNull @PathParam("id") String storeId) {

        StoreDto storeDto = storeFacade.getStoreById(storeId);
        return Response.ok().entity(storeDto).build();
    }

    @POST
    @ApiOperation(value = "Create store", response = StoreDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create store successfully")
    })
    public Response createStore(@NotNull CreateStoreCommand createStoreCommand) {
        String id = storeFacade.createStore(createStoreCommand);

        return Response.created(URI.create("/" + id)).build();
    }

    @PUT
    @Path("{id}")
    @ApiOperation(value = "Update store info", response = StoreDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update store info successfully"),
            @ApiResponse(code = 404, message = "Could not find store by id")
    })
    public Response updateStoreInfo(@NotNull @PathParam("id") String storeId,
                                    @NotNull UpdateStoreCommand command) {
        storeFacade.updateStore(storeId, command);

        return Response.ok().build();
    }
}
