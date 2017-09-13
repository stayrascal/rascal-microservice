package com.stayrascal.cloud.order.contract.client;

import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.order.contract.command.CreateTransactionCommand;
import com.stayrascal.cloud.order.contract.dto.TransactionDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.core.MediaType;

@FeignClient(value = "service-order", fallback = TransactionServiceClient.HystrixClientFallback.class)
public interface TransactionServiceClient {

    @RequestMapping(method = RequestMethod.POST, path = "/rest/transactions", consumes = MediaType.APPLICATION_JSON)
    CreatedResult createTransaction(CreateTransactionCommand command);

    @RequestMapping(method = {RequestMethod.GET}, value = {"/rest/transactions/{id}"},
            produces = MediaType.APPLICATION_JSON)
    TransactionDto getTransaction(@PathVariable("id") String transactionId);

    static class HystrixClientFallback implements TransactionServiceClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallback.class);

        @Override
        public CreatedResult createTransaction(CreateTransactionCommand command) {
            HystrixClientFallback.LOGGER.info("Throw Exception when create transaction, enter fallback method, order id = {}", command.getOrderId());
            return new CreatedResult("-1");
        }

        @Override
        public TransactionDto getTransaction(String transactionId) {
            HystrixClientFallback.LOGGER.info("Throw Exception when retrieve transaction, enter fallback method, transaction id = {}", transactionId);
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setId("-1");
            return transactionDto;
        }
    }
}
