package com.stayrascal.cloud.order.domain.entity;

import com.stayrascal.cloud.common.lib.constant.ErrorCode;
import com.stayrascal.cloud.common.lib.jersey.exception.BadRequestException;
import com.stayrascal.cloud.order.contract.enumeration.TransactionClientType;
import com.stayrascal.cloud.order.contract.enumeration.TransactionProvider;
import com.stayrascal.cloud.order.contract.enumeration.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class Transaction {
    private static final Logger LOGGER = LoggerFactory.getLogger(Transaction.class);

    private String id;

    private String orderId;

    private TransactionProvider provider;

    private TransactionClientType clientType;

    private BigDecimal totalAmount;

    private String thirdPartyTransactionId;

    private Map<String, String> thirdPartyTransactionInfo;

    private Date timeCreated;

    private TransactionStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public TransactionProvider getProvider() {
        return provider;
    }

    public void setProvider(TransactionProvider provider) {
        this.provider = provider;
    }

    public TransactionClientType getClientType() {
        return clientType;
    }

    public void setClientType(TransactionClientType clientType) {
        this.clientType = clientType;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getThirdPartyTransactionId() {
        return thirdPartyTransactionId;
    }

    public void setThirdPartyTransactionId(String thirdPartyTransactionId) {
        this.thirdPartyTransactionId = thirdPartyTransactionId;
    }

    public Map<String, String> getThirdPartyTransactionInfo() {
        return thirdPartyTransactionInfo;
    }

    public void setThirdPartyTransactionInfo(Map<String, String> thirdPartyTransactionInfo) {
        this.thirdPartyTransactionInfo = thirdPartyTransactionInfo;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public void confirm(TransactionProvider provider, String thirdPartyTransactionId, Double totalAmount) {
        if (provider != getProvider()) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "Provider not match: {} vs {}, of transaction {}",
                    provider, getProvider(), getId());
        }

        // TODO: check total amount
        setThirdPartyTransactionId(thirdPartyTransactionId);
        setStatus(TransactionStatus.CONFIRMED);
    }
}
