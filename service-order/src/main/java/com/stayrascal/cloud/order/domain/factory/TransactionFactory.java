package com.stayrascal.cloud.order.domain.factory;


import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.jersey.exception.BadRequestException;
import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.order.contract.enumeration.TransactionClientType;
import com.stayrascal.cloud.order.contract.enumeration.TransactionProvider;
import com.stayrascal.cloud.order.contract.enumeration.TransactionStatus;
import com.stayrascal.cloud.order.contract.enumeration.WeChatTradeType;
import com.stayrascal.cloud.order.domain.entity.Order;
import com.stayrascal.cloud.order.domain.entity.Transaction;
import com.stayrascal.cloud.order.domain.mapper.OrderDtoMapper;
import com.stayrascal.cloud.order.service.WeChatService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class TransactionFactory {
    private static final Logger LOG = LoggerFactory.getLogger(TransactionFactory.class);

    private final UniqueKeyGenerator keyGenerator;
    private final OrderDtoMapper orderDtoMapper;
    private final WeChatService weChatService;

    @Autowired
    public TransactionFactory(UniqueKeyGenerator keyGenerator, OrderDtoMapper orderDtoMapper,
                              WeChatService weChatService) {
        this.keyGenerator = keyGenerator;
        this.orderDtoMapper = orderDtoMapper;
        this.weChatService = weChatService;
    }

    public Transaction createTransaction(Order order, TransactionProvider transactionProvider,
                                         TransactionClientType transactionClientType, Map<String, String> params) {

        Transaction transaction = new Transaction();
        String transactionId = keyGenerator.generateKey().replaceAll("-", "");
        transaction.setId(transactionId);
        transaction.setOrderId(order.getId());
        transaction.setProvider(transactionProvider);
        transaction.setClientType(transactionClientType);
        BigDecimal totalAmount = order.getTotalAmount();
        transaction.setTotalAmount(totalAmount);

        Map<String, String> transactionInfo = generateThirdPartyTransactionInfo(transactionId,
                transactionProvider, transactionClientType, totalAmount, params);

        transaction.setThirdPartyTransactionInfo(transactionInfo);
        transaction.setTimeCreated(DateTime.now().toDate());
        transaction.setStatus(TransactionStatus.OPEN);
        return transaction;
    }

    private Map<String, String> generateThirdPartyTransactionInfo(String transactionId,
                                                                  TransactionProvider transactionProvider,
                                                                  TransactionClientType transactionClientType,
                                                                  BigDecimal totalAmount,
                                                                  Map<String, String> params) {

        if (transactionProvider != TransactionProvider.WECHAT && transactionClientType != TransactionClientType.JSAPI) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "Unsupported transaction provider {} and client type {}",
                    transactionProvider, transactionClientType);
        }

        return weChatService.purchase(params.get("openId"), WeChatTradeType.JSAPI, params.get("ip"),
                totalAmount, transactionId);
    }
}

