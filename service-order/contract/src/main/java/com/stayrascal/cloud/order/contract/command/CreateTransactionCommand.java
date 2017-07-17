package com.stayrascal.cloud.order.contract.command;

import com.stayrascal.cloud.order.contract.enumeration.TransactionClientType;
import com.stayrascal.cloud.order.contract.enumeration.TransactionProvider;

import java.util.Map;

public class CreateTransactionCommand {
    private String orderId;
    private TransactionProvider provider;
    private TransactionClientType clientType;

    private Map<String, String> params;

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

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
