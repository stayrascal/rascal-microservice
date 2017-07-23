package com.stayrascal.cloud.order.domain.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.order.contract.dto.TransactionDto;
import com.stayrascal.cloud.order.domain.entity.Transaction;

import org.springframework.stereotype.Component;

@Component
public class TransactionDtoMapper extends DefaultMapper {
    public TransactionDtoMapper() {
        register(TransactionDto.class, Transaction.class);
    }

    public TransactionDto transactionToDto(Transaction transaction) {
        return map(transaction, TransactionDto.class);
    }
}
