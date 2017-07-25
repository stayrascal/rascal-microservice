package com.stayrascal.cloud.auth.resource;

import com.stayrascal.cloud.auth.contract.command.CreateAuthenticationCommand;
import com.stayrascal.cloud.auth.contract.dto.AuthenticationDto;
import com.stayrascal.cloud.auth.facade.AuthenticationFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("auths")
@Api(value = "auth", description = "Access to auth resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    private AuthenticationFacade authenticationFacade;

    @Autowired
    public AuthResource(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Path("/{id}")
    @ApiOperation(value = "Get auth by id", response = AuthenticationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get auth successfully"),
            @ApiResponse(code = 404, message = "No auth matches given id")
    })
    @GET
    public Response getAuth(@NotNull @PathParam("id") String authId) {

        AuthenticationDto authDto = authenticationFacade.getAuthById(authId);
        return Response.ok().entity(authDto).build();
    }

    @POST
    @ApiOperation(value = "Create auth", response = AuthenticationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create auth successfully")
    })
    public Response createAuth(@NotNull CreateAuthenticationCommand createAuthenticationCommand) {
        return null;
    }
}
