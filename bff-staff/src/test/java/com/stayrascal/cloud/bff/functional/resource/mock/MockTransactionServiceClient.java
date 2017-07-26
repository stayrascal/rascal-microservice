package com.stayrascal.cloud.bff.functional.resource.mock;

import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.order.contract.client.TransactionServiceClient;
import com.stayrascal.cloud.order.contract.command.CreateTransactionCommand;
import com.stayrascal.cloud.order.contract.dto.TransactionDto;

import org.springframework.stereotype.Component;

@Component
public class MockTransactionServiceClient implements TransactionServiceClient {
    private String json = "";

    @Override
    public CreatedResult createTransaction(CreateTransactionCommand command) {
        return null;
    }

    @Override
    public TransactionDto getTransaction(String transactionId) {
        return MockUtils.fromJson(json, TransactionDto.class);
    }

    public void setJson(String json) {
        this.json = json;
    }
}
