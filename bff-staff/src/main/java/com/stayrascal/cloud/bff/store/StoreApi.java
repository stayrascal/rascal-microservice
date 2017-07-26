package com.stayrascal.cloud.bff.store;


import static com.stayrascal.cloud.common.jersey.JerseyHelpers.uriOfCreated;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.organization.contract.client.StoreServiceClient;
import com.stayrascal.cloud.organization.contract.command.CreateStoreCommand;
import com.stayrascal.cloud.organization.contract.command.UpdateStoreCommand;

import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@Path("stores")
@Api(value = "store", description = "Access to store resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoreApi {
    private StoreServiceClient storeServiceClient;

    private StoreService storeService;

    @Autowired
    public StoreApi(StoreServiceClient storeServiceClient, StoreService storeService) {
        this.storeServiceClient = storeServiceClient;
        this.storeService = storeService;
    }

    @GET
    @Path("get/{id}")
    public Response getStore(@PathParam("id") String id) {
        return Response.ok().entity(storeService.get(id)).build();
    }

    @GET
    @Path("list/all")
    public Response listStore(@QueryParam("province_id") Long provinceId,
                              @QueryParam("city_id") Long cityId,
                              @QueryParam("from_date") Long fromDate,
                              @QueryParam("to_date") Long toDate,
                              @QueryParam("name") String name,
                              @QueryParam("sort_type") SortType sortType,
                              @QueryParam("sort_by") String sortBy,
                              @QueryParam("page_size") Integer pageSize,
                              @QueryParam("page_index") Integer pageIndex) {
        return Response.ok()
                .entity(storeService.list(provinceId, cityId, fromDate, toDate, name,
                        sortType == null ? SortType.ASC : sortType, Strings.isNullOrEmpty(sortBy) ? "timeCreated" : sortBy,
                        pageSize, pageIndex))
                .build();
    }

    @POST
    public Response createStore(CreateStoreCommand command, @Context UriInfo uriInfo) {
        CreatedResult createdResult = storeServiceClient.createStore(command);
        return Response.created(uriOfCreated(uriInfo, createdResult.getId())).entity(createdResult).build();
    }

    @PUT
    @Path("update/{id}")
    public Response updateStore(@PathParam("id") String id, UpdateStoreCommand command) {
        storeServiceClient.updateStore(id, command);
        return Response.ok().build();
    }

//    @DELETE
//    @Path("delete/{id}")
//    public Response deleteStore(@PathParam("id") String id) {
//        storeServiceClient.deleteStore(id);
//        return Response.ok().build();
//    }
}
