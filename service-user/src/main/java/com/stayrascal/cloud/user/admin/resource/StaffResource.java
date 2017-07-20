package com.stayrascal.cloud.user.admin.resource;

import com.stayrascal.cloud.user.admin.facade.StaffFacade;
import com.stayrascal.clould.common.contract.enumeration.SortType;
import com.stayrascal.clould.common.contract.result.CreatedResult;
import com.stayrascal.clould.common.contract.result.PageResult;
import com.stayrscal.cloud.user.admin.contract.command.CreateStaffCommand;
import com.stayrscal.cloud.user.admin.contract.dto.StaffDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Component
@Path("staffs")
@Api(value = "staffs", description = "Access to staff resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StaffResource {
    private StaffFacade staffFacade;

    @Autowired
    public StaffResource(StaffFacade staffFacade) {
        this.staffFacade = staffFacade;
    }

    @GET
    @Path("{id}")
    @ApiOperation(value = "get staff info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = StaffDto.class, message = "Get staff successfully"),
            @ApiResponse(code = 404, message = "No staff matches given id")
    })
    public StaffDto getStaff(@PathParam("id") String id) {
        return staffFacade.getStaff(id);
    }

    @POST
    @ApiOperation(value = "create staff")
    @ApiResponses(value = {
            @ApiResponse(code = 201, response = CreatedResult.class, message = "Create staff successfully")
    })
    public Response createStaff(CreateStaffCommand command) {
        String id = staffFacade.createStaff(command);
        return Response.created(URI.create("/" + id)).entity(new CreatedResult(id)).build();
    }

    @GET
    @ApiOperation(value = "List staffs")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = PageResult.class, message = "List staff successfully")
    })
    public PageResult listStaffs(@ApiParam(required = true) @QueryParam("sort_type") @NotNull SortType sortType,
                                 @ApiParam(required = true) @QueryParam("sort_by") @NotNull String sortBy,
                                 @ApiParam(required = true) @QueryParam("page_size") @NotNull Integer pageSize,
                                 @ApiParam(required = true) @QueryParam("page_index") @NotNull Integer pageIndex,
                                 @QueryParam("organization_id") String organizationId,
                                 @QueryParam("role_id") String roleId) {
        Long totalCount = staffFacade.countStaffs(organizationId, roleId);
        List<StaffDto> staffs = staffFacade.listStaffs(sortType, sortBy, pageSize, pageIndex, organizationId, roleId);
        return new PageResult(totalCount, pageSize, pageIndex, staffs);
    }
}
