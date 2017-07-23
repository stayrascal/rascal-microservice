package com.stayrascal.cloud.user.auth.resource;

import com.stayrascal.cloud.common.jersey.JerseyHelpers;
import com.stayrascal.cloud.user.auth.facade.RoleFacade;
import com.stayrascal.clould.common.contract.enumeration.SortType;
import com.stayrascal.clould.common.contract.result.CreatedResult;
import com.stayrascal.clould.common.contract.result.PageResult;

import com.stayrscal.cloud.user.auth.contract.command.CreateRoleCommand;
import com.stayrscal.cloud.user.auth.contract.command.CreateRolePermissionCommand;
import com.stayrscal.cloud.user.auth.contract.dto.RoleDto;
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
@Path("roles")
@Api(value = "roles", description = "Access to role resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoleResource {

    @Autowired
    private RoleFacade roleFacade;

    @GET
    @Path("/{id}")
    @ApiOperation("Get user roles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get role successfully", response = RoleDto.class)
    })
    public RoleDto getRole(@ApiParam(required = true) @PathParam("id") String roleId) {
        return roleFacade.getRole(roleId);
    }

    @POST
    @ApiOperation(value = "Create role", response = CreatedResult.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create role successfully")
    })
    public Response createRole(@NotNull CreateRoleCommand command, @Context UriInfo uriInfo) {
        String id = roleFacade.createRole(command);
        return Response.created(JerseyHelpers.uriOfCreated(uriInfo, id)).entity(new CreatedResult(id)).build();
    }

    @GET
    @ApiOperation("List user roles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List roles successfully", response = PageResult.class)
    })
    public PageResult listRoles(@ApiParam(required = true) @NotNull @QueryParam("page_size") Integer pageSize,
                                @ApiParam(required = true) @NotNull @QueryParam("page_index") Integer pageIndex,
                                @ApiParam(required = true) @NotNull @QueryParam("sort_type") SortType sortType,
                                @ApiParam(required = true) @NotNull @QueryParam("sort_by") String sortBy,
                                @QueryParam("name") String name) {
        Long totalCount = roleFacade.countRoles(name);
        List<RoleDto> roles = roleFacade.listRoles(pageSize, pageIndex, sortType, sortBy, name);

        return new PageResult(totalCount, pageSize, pageIndex, roles);
    }

    @POST
    @Path("/{id}/permissions")
    @ApiOperation(value = "Create role permission", response = CreatedResult.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create role permission successfully")
    })
    public Response createRolePermission(@ApiParam(required = true) @NotNull @PathParam("id") String roleId,
                                         @NotNull CreateRolePermissionCommand command,
                                         @Context UriInfo uriInfo) {
        Long permissionId = roleFacade.createRolePermission(roleId, command);
        String idStr = String.valueOf(permissionId);
        return Response.created(JerseyHelpers.uriOfCreated(uriInfo, idStr)).entity(new CreatedResult(idStr)).build();
    }

    @DELETE
    @Path("/{role_id}/permissions/{permission_id}")
    @ApiOperation(value = "Delete role permission")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Delete role permission successfully")
    })
    public void deleteRolePermission(@ApiParam(required = true) @NotNull @PathParam("role_id") String roleId,
                                     @ApiParam(required = true) @NotNull @PathParam("permission_id") String permissionId) {
        roleFacade.deleteRolePermission(roleId, permissionId);
    }
}
