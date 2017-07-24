package com.stayrascal.cloud.address.resource;

import com.stayrascal.cloud.address.contract.command.CreateAddressCommand;
import com.stayrascal.cloud.address.contract.command.UpdateAddressCommand;
import com.stayrascal.cloud.address.contract.dto.AddressDto;
import com.stayrascal.cloud.address.facade.AddressFacade;

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
@Path("addresss")
@Api(value = "address", description = "Access to address resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource {
    private AddressFacade addressFacade;

    @Autowired
    public AddressResource(AddressFacade addressFacade) {
        this.addressFacade = addressFacade;
    }

    @Path("/{id}")
    @ApiOperation(value = "Get address by id", response = AddressDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Get address successfully"),
        @ApiResponse(code = 404, message = "No address matches given id")
    })
    @GET
    public Response getAddress(@NotNull @PathParam("id") String addressId) {

        AddressDto addressDto = addressFacade.getAddressById(addressId);
        return Response.ok().entity(addressDto).build();
    }

    @POST
    @ApiOperation(value = "Create address", response = AddressDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Create address successfully")
    })
    public Response createAddress(@NotNull CreateAddressCommand createAddressCommand) {
        String id = addressFacade.createAddress(createAddressCommand);

        return Response.created(URI.create("/" + id)).build();
    }

    @PUT
    @Path("{id}")
    @ApiOperation(value = "Update address info", response = AddressDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Update address info successfully"),
        @ApiResponse(code = 404, message = "Could not find address by id")
    })
    public Response updateAddressInfo(@NotNull @PathParam("id") String addressId,
                                      @NotNull UpdateAddressCommand command) {
        addressFacade.updateAddressInfo(addressId, command);

        return Response.ok().build();
    }
}
