package com.stayrascal.cloud.order.facade;

import com.stayrascal.cloud.common.lib.constant.ErrorCode;
import com.stayrascal.cloud.common.lib.jersey.exception.BadRequestException;
import com.stayrascal.cloud.common.lib.jersey.exception.NotFoundException;
import com.stayrascal.cloud.order.contract.command.CreateTransactionCommand;
import com.stayrascal.cloud.order.contract.dto.TransactionDto;
import com.stayrascal.cloud.order.contract.enumeration.TransactionProvider;
import com.stayrascal.cloud.order.domain.entity.Order;
import com.stayrascal.cloud.order.domain.entity.Transaction;
import com.stayrascal.cloud.order.domain.factory.TransactionFactory;
import com.stayrascal.cloud.order.domain.mapper.TransactionDtoMapper;
import com.stayrascal.cloud.order.domain.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class TransactionFacade {
    public static final Logger LOGGER = LoggerFactory.getLogger(TransactionFacade.class);

    private final TransactionFactory transactionFactory;
    private final TransactionRepository transactionRepository;
    private final OrderFacade orderFacade;
    private final TransactionDtoMapper transactionDtoMapper;

    @Autowired
    public TransactionFacade(TransactionFactory transactionFactory, TransactionRepository transactionRepository, OrderFacade orderFacade, TransactionDtoMapper transactionDtoMapper) {
        this.transactionFactory = transactionFactory;
        this.transactionRepository = transactionRepository;
        this.orderFacade = orderFacade;
        this.transactionDtoMapper = transactionDtoMapper;
    }

    public String createTransaction(CreateTransactionCommand command) {
        Order order = orderFacade.getOrderById(command.getOrderId());
        Transaction transaction = transactionFactory.createTransaction(order, command.getProvider(), command.getClientType(), command.getParams());
        return transactionRepository.insert(transaction);
    }

    public TransactionDto getTransaction(String transactionId) {
        Transaction transaction = getTransactionById(transactionId);
        return transactionDtoMapper.transactionToDto(transaction);
    }

    private Transaction getTransactionById(String transactionId) {
        return transactionRepository.ofId(transactionId).orElseThrow(() -> new NotFoundException(
                ErrorCode.INTERNAL_ERROR, "Transaction of id {} not found", transactionId));
    }

    public void confirmtransaction(String tranactionId, TransactionProvider provider, String thirdPartyTransactionId, Double totalAmount) {
        Optional<Transaction> transactionOptional = transactionRepository.ofId(tranactionId);
        if (!transactionOptional.isPresent()) {
            // TODO: should we throw exception in this case? how can we track this payment?
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "Failed to confirm transaction, due to id {} not found", tranactionId);
        }
        Transaction transaction = transactionOptional.get();
        transaction.confirm(provider, thirdPartyTransactionId, totalAmount);

        transactionRepository.update(transaction);

        orderFacade.finishTransaction(transaction.getOrderId(), tranactionId);
    }
}
