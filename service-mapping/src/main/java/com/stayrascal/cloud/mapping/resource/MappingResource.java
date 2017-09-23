package com.stayrascal.cloud.mapping.resource;

import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.mapping.contract.command.CreateStaffAddressMappingCommand;
import com.stayrascal.cloud.mapping.contract.command.CreateStaffOrderMappingCommand;
import com.stayrascal.cloud.mapping.facade.MappingFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("mappings")
@Api(value = "mappings", description = "Access to mappings resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MappingResource {
    private MappingFacade mappingFacade;

    @Autowired
    public MappingResource(MappingFacade mappingFacade) {
        this.mappingFacade = mappingFacade;
    }

    @POST
    @Path("/add/address")
    public Response addAddress(CreateStaffAddressMappingCommand command) {
        String userId = mappingFacade.addAddressMapping(command);
        return Response.created(URI.create("/" + userId)).entity(new CreatedResult(userId)).build();
    }

    @POST
    @Path("/add/order")
    public Response addOrder(CreateStaffOrderMappingCommand command) {
        String userId = mappingFacade.addOrderMapping(command);
        return Response.created(URI.create("/" + userId)).entity(new CreatedResult(userId)).build();
    }

    @GET
    @Path("/addressId/{userId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = List.class, message = "Get address ids successfully"),
            @ApiResponse(code = 404, message = "No address ids matches given user id")
    })
    public PageResult retrieveAddressIds(@PathParam("userId") String userId) {
        List<Long> mappings = mappingFacade.getAddressIdByStaffId(userId);
        return new PageResult((long) mappings.size(), 0, mappings.size(), mappings);
    }


    @GET
    @Path("/orderId/{userId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = List.class, message = "Get order ids successfully"),
            @ApiResponse(code = 404, message = "No order ids matches given user id")
    })
    public PageResult retrieveOrderIds(@PathParam("userId") String userId) {
        List<String> mappings = mappingFacade.getOrderIdByStaffId(userId);
        return new PageResult((long) mappings.size(), 0, mappings.size(), mappings);
    }
}
