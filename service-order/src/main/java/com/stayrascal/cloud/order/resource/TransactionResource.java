package com.stayrascal.cloud.order.resource;

import static com.stayrascal.cloud.common.jersey.JerseyHelpers.uriOfCreated;

import com.stayrascal.cloud.order.contract.command.CreateTransactionCommand;
import com.stayrascal.cloud.order.contract.dto.TransactionDto;
import com.stayrascal.cloud.order.facade.TransactionFacade;
import com.stayrascal.clould.common.contract.result.CreatedResult;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Component
@Path("transactions")
@Api(value = "Transaction", description = "Access to transaction resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {
    private final TransactionFacade transactionFacade;

    @Autowired
    public TransactionResource(TransactionFacade transactionFacade) {
        this.transactionFacade = transactionFacade;
    }

    @POST
    @ApiOperation(value = "Create transaction")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create transaction successfully", response = CreatedResult.class)
    })
    public Response createTransaction(@NotNull CreateTransactionCommand command, @Context UriInfo uriInfo) {
        String id = transactionFacade.createTransaction(command);
        return Response.created(uriOfCreated(uriInfo, id)).entity(new CreatedResult(id)).build();
    }

    @GET
    @Path("/{id}")
    @ApiOperation(value = "Get transaction by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get transaction successfully", response = TransactionDto.class),
            @ApiResponse(code = 404, message = "No transaction matches given id")
    })
    public TransactionDto getTransaction(@NotNull @PathParam("id") String transactionId) {
        return transactionFacade.getTransaction(transactionId);
    }
}
