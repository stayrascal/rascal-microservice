package com.stayrascal.cloud.user.auth.resource;

import com.stayrascal.cloud.common.contract.auth.Identity;
import com.stayrascal.cloud.common.contract.auth.IdentityType;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.common.jersey.JerseyHelpers;
import com.stayrascal.cloud.user.auth.facade.AuthFacade;

import com.stayrscal.cloud.user.auth.contract.AuthenticationType;
import com.stayrscal.cloud.user.auth.contract.command.CreateAuthenticationCommand;
import com.stayrscal.cloud.user.auth.contract.command.CreateAuthenticationKeyCommand;
import com.stayrscal.cloud.user.auth.contract.command.CreateTokenCommand;
import com.stayrscal.cloud.user.auth.contract.dto.AuthenticationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@Path("authentications")
@Api(value = "authentication", description = "Access to authentication resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {
    @Autowired
    private AuthFacade authFacade;

    @GET
    @Path("/{id}")
    @ApiOperation("Get user authentications")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get authentication successfully", response = AuthenticationDto.class)
    })
    public AuthenticationDto getAuthentication(@ApiParam(required = true) @PathParam("id") String authenticationId) {
        return authFacade.getAuthentication(authenticationId);
    }

    @POST
    @ApiOperation(value = "Create authentication", response = CreatedResult.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create authentication successfully")
    })
    public Response createAuthentication(@NotNull CreateAuthenticationCommand command, @Context UriInfo uriInfo) {
        String id = authFacade.createAuthentication(command);
        return Response.created(JerseyHelpers.uriOfCreated(uriInfo, id)).entity(new CreatedResult(id)).build();
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("Delete user authentication")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Delete authentication successfully"),
            @ApiResponse(code = 404, message = "Couldn't find authentication"),
            @ApiResponse(code = 400, message = "IdentityDto must at latest has one authentication")
    })
    public void deleteAuthentication(@ApiParam(required = true) @NotNull @PathParam("id") String authenticationId) {
        authFacade.delete(authenticationId);
    }

    @GET
    @ApiOperation("List user authentications")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List authentication successfully", response = PageResult.class)
    })
    public PageResult listAuthentications(@ApiParam(required = true) @NotNull @QueryParam("page_size") Integer pageSize,
                                          @ApiParam(required = true) @NotNull @QueryParam("page_index") Integer pageIndex,
                                          @ApiParam(required = true) @NotNull @QueryParam("sort_type") SortType sortType,
                                          @ApiParam(required = true) @NotNull @QueryParam("sort_by") String sortBy,
                                          @QueryParam("identity_type") IdentityType identityType,
                                          @QueryParam("identity_id") String identityId,
                                          @QueryParam("authentication_name") String authenticationName,
                                          @QueryParam("authentication_type") AuthenticationType authenticationType) {
        Long totalCount = authFacade.countAuthentications(identityType, identityId, authenticationName,
                authenticationType);
        List<AuthenticationDto> authenticationDtoList = authFacade.listAuthentications(pageSize, pageIndex,
                sortType, sortBy, identityType, identityId, authenticationName, authenticationType);

        return new PageResult(totalCount, pageSize, pageIndex, authenticationDtoList);
    }


    @POST
    @Path("{auth-id}/keys")
    @ApiOperation(
            value = "Create authentication key on given authentication, not all authentication types support multi keys"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create authentication key successfully", response = CreatedResult.class),
            @ApiResponse(code = 404, message = "Multi keys not supported")
    })
    public Response createAuthenticationKey(@ApiParam(required = true)
                                            @NotNull @PathParam("auth-id") String authenticationId,
                                            @NotNull CreateAuthenticationKeyCommand command,
                                            @Context UriInfo uriInfo) {

        String id = String.valueOf(authFacade.createAuthenticationKey(authenticationId, command));

        return Response.created(JerseyHelpers.uriOfCreated(uriInfo, id)).entity(new CreatedResult(id)).build();
    }

    @POST
    @Path("{auth-id}/tokens")
    @ApiOperation(
            value = "create token by user authentication and key, the token created encodes the identity"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "create token successfully", response = CreatedResult.class),
            @ApiResponse(code = 401, message = "could not create token")
    })
    public Response createToken(@ApiParam(required = true) @NotNull @PathParam("auth-id") String authenticationId,
                                @NotNull CreateTokenCommand command,
                                @Context UriInfo uriInfo) {
        String token = authFacade.createToken(authenticationId, command);

        return Response.created(JerseyHelpers.uriOfCreated(uriInfo, token)).entity(new CreatedResult(token)).build();
    }

    @GET
    @Path("{auth-id}/tokens/{token}")
    @ApiOperation(value = "verify token and return identity message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get identity successfully", response = Identity.class),
            @ApiResponse(code = 403, message = "Invalid token")
    })
    public Identity getToken(@ApiParam(required = true) @NotNull @PathParam("auth-id") String authenticationId,
                             @ApiParam(required = true) @NotNull @PathParam("token") String token) {
        return authFacade.getTokenEncodedIdentity(authenticationId, token);
    }
}
