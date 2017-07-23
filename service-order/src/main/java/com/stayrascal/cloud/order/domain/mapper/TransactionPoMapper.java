package com.stayrascal.cloud.order.domain.mapper;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.jersey.exception.ServerErrorException;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.order.domain.entity.Transaction;
import com.stayrascal.cloud.order.infrastructure.persistence.po.TransactionPo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class TransactionPoMapper extends DefaultMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionPoMapper.class);

    public TransactionPoMapper() {
        register(TransactionPo.class, Transaction.class);
    }

    public TransactionPo transactionToPo(Transaction transaction) {
        final TransactionPo transactionPo = map(transaction, TransactionPo.class);
        if (transaction.getThirdPartyTransactionInfo() != null) {
            try {
                transactionPo.setThirdPartyTransactionInfo(new ObjectMapper().writeValueAsString(
                        transaction.getThirdPartyTransactionInfo()));
            } catch (JsonProcessingException e) {
                LOGGER.error("fail to mapper thirdPartyTransactionInfo. {}", transaction.getThirdPartyTransactionInfo());
                throw new ServerErrorException(ErrorCode.INTERNAL_ERROR, "fail to mapper thirdPartyTransactionInfo. {}",
                        transaction.getThirdPartyTransactionInfo());
            }
        }
        return transactionPo;
    }

    public Transaction transactionFromPo(TransactionPo transactionPo) {
        final Transaction transaction = map(transactionPo, Transaction.class);
        if (transactionPo.getThirdPartyTransactionInfo() != null) {
            try {
                transaction.setThirdPartyTransactionInfo(new ObjectMapper().readValue(
                        transactionPo.getThirdPartyTransactionInfo(), Map.class));
            } catch (IOException e) {
                LOGGER.error("fail to mapper thirdPartyTransactionInfo. {}", transactionPo.getThirdPartyTransactionInfo());
                throw new ServerErrorException(ErrorCode.INTERNAL_ERROR, "fail to mapper thirdPartyTransactionInfo. {}",
                        transactionPo.getThirdPartyTransactionInfo());
            }
        }
        return transaction;
    }
}
