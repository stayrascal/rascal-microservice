package com.stayrascal.cloud.order.contract.dto;

import com.stayrascal.cloud.order.contract.enumeration.TransactionClientType;
import com.stayrascal.cloud.order.contract.enumeration.TransactionProvider;
import com.stayrascal.cloud.order.contract.enumeration.TransactionStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class TransactionDto {
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
}
