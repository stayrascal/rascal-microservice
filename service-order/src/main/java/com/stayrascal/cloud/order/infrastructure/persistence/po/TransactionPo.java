package com.stayrascal.cloud.order.infrastructure.persistence.po;

import com.stayrascal.cloud.common.jpa.BasePo;
import com.stayrascal.cloud.order.contract.enumeration.TransactionClientType;
import com.stayrascal.cloud.order.contract.enumeration.TransactionProvider;
import com.stayrascal.cloud.order.contract.enumeration.TransactionStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "TRANSACTION")
public class TransactionPo extends BasePo {
    @Column(name = "order_id", length = 64, nullable = false, updatable = false)
    private String orderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", length = 32, nullable = false)
    private TransactionProvider provider;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_type", length = 32, nullable = false)
    private TransactionClientType clientType;

    @Column(name = "total_amount", nullable = false,
            updatable = false, precision = 2,
            scale = 15, columnDefinition = "decimal")
    private BigDecimal totalAmount;

    @Column(name = "third_party_transaction_id", length = 64)
    private String thirdPartyTransactionId;

    @Column(name = "third_party_transaction_info", length = 2048, updatable = false)
    private String thirdPartyTransactionInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private TransactionStatus status;

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

    public String getThirdPartyTransactionInfo() {
        return thirdPartyTransactionInfo;
    }

    public void setThirdPartyTransactionInfo(String thirdPartyTransactionInfo) {
        this.thirdPartyTransactionInfo = thirdPartyTransactionInfo;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
