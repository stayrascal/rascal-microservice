package com.stayrascal.cloud.organization.resource;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.organization.contract.command.CreateOrganizationCommand;
import com.stayrascal.cloud.organization.contract.command.UpdateOrganizationCommand;
import com.stayrascal.cloud.organization.contract.dto.OrganizationDto;
import com.stayrascal.cloud.organization.facade.OrganizationFacade;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("organizations")
@Api(value = "organization", description = "Access to organization resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrganizationResource {
    private OrganizationFacade organizationFacade;

    @Autowired
    public OrganizationResource(OrganizationFacade organizationFacade) {
        this.organizationFacade = organizationFacade;
    }

    @Path("/{id}")
    @ApiOperation(value = "Get organization by id", response = OrganizationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get organization successfully"),
            @ApiResponse(code = 404, message = "No organization matches given id")
    })
    @GET
    public Response getOrganization(@NotNull @PathParam("id") String organizationId) {

        OrganizationDto organizationDto = organizationFacade.getOrganizationById(organizationId);
        return Response.ok().entity(organizationDto).build();
    }

    @POST
    @ApiOperation(value = "Create organization", response = OrganizationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create organization successfully")
    })
    public Response createOrganization(@NotNull CreateOrganizationCommand createOrganizationCommand) {
        String id = organizationFacade.createOrganization(createOrganizationCommand);

        return Response.created(URI.create("/" + id)).build();
    }

    @PUT
    @Path("/{id}")
    @ApiOperation(value = "Update organization info", response = OrganizationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update organization info successfully"),
            @ApiResponse(code = 404, message = "Could not find organization by id")
    })
    public OrganizationDto updateOrganizationInfo(@NotNull @PathParam("id") String organizationId,
                                                  @NotNull UpdateOrganizationCommand command) {
        return organizationFacade.updateOrganization(organizationId, command);
    }

    @GET
    @ApiOperation(value = "List Organizations", response = PageResult.class)
    PageResult listOrganizations(@RequestParam("sort_type") SortType sortType,
                                 @RequestParam("sort_by") String sortBy,
                                 @RequestParam("page_size") Integer pageSize,
                                 @RequestParam("page_index") Integer pageIndex,
                                 @RequestParam Map<String, String> queryMap) {
        SortQuery sortQuery = new SortQuery(sortType, sortBy, pageSize, pageIndex);
        List<OrganizationDto> organizationDtos = organizationFacade.listOrganizations(sortQuery, queryMap);
        return new PageResult((long) organizationDtos.size(), pageSize, pageIndex, organizationDtos);
    }
}
