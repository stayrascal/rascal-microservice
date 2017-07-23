package com.stayrascal.cloud.order.contract.client;

import com.stayrascal.cloud.order.contract.command.CreateTransactionCommand;
import com.stayrascal.cloud.order.contract.dto.TransactionDto;
import com.stayrascal.clould.common.contract.result.CreatedResult;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.core.MediaType;

@FeignClient(value = "service-order")
public interface TransactionServiceClient {

    @RequestMapping(method = RequestMethod.POST, path = "/rest/transactions", consumes = MediaType.APPLICATION_JSON)
    CreatedResult createTransaction(CreateTransactionCommand command);

    @RequestMapping(method = {RequestMethod.GET}, value = {"/rest/transactions/{id}"},
            produces = MediaType.APPLICATION_JSON)
    TransactionDto getTransaction(@PathVariable("id") String transactionId);
}
