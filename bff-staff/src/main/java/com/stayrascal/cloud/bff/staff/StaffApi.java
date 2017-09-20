package com.stayrascal.cloud.bff.staff;

import com.stayrascal.cloud.bff.staff.request.CreateClerkCommand;
import com.stayrascal.cloud.bff.staff.response.ClerkResponse;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;

import io.swagger.annotations.Api;
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

@Component
@Path("staff")
@Api(value = "staff", description = "Access to staff resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StaffApi {

    @Autowired
    private StaffService staffService;

    @POST
    @Path("clerks-in-{store_id}")
    public Response createClerk(@PathParam("store_id") String storeId, CreateClerkCommand command) {
        final CreatedResult result = staffService.createClerk(storeId, command);
        return Response.ok().entity(result).build();
    }

    @GET
    @Path("clerks-in-{store_id}")
    public Response listClerk(@PathParam("store_id") String storeId,
                              @NotNull @QueryParam("page_size") Integer pageSize,
                              @NotNull @QueryParam("page_index") Integer pageIndex) {
        final PageResult result = staffService.listClerk(storeId, pageSize, pageIndex);
        return Response.ok().entity(result).build();
    }

    @GET
    @Path("{clerk_id}")
    public Response getClerk(@PathParam("clerk_id") String clerkId) {
        final ClerkResponse clerk = staffService.getClerk(clerkId);
        return Response.ok().entity(clerk).build();
    }

    @GET
    @Path("/list")
    public Response listClerks(@NotNull @QueryParam("page_size") Integer pageSize,
                               @NotNull @QueryParam("page_index") Integer pageIndex) {
        final PageResult result = staffService.listClerk(pageSize, pageIndex);
        return Response.ok().entity(result).build();
    }
}
