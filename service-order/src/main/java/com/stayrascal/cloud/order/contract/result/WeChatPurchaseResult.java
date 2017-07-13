package com.stayrascal.cloud.order.contract.result;

import com.stayrascal.cloud.order.contract.enumeration.WeChatTradeType;

public class WeChatPurchaseResult {
    private String transactionId;
    private String weChatTradeId;
    private Double totalAmount;
    private WeChatTradeType tradeType;

    public WeChatPurchaseResult(String transactionId, String weChatTradeId, Double totalAmount, WeChatTradeType tradeType) {
        this.transactionId = transactionId;
        this.weChatTradeId = weChatTradeId;
        this.totalAmount = totalAmount;
        this.tradeType = tradeType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getWeChatTradeId() {
        return weChatTradeId;
    }

    public void setWeChatTradeId(String weChatTradeId) {
        this.weChatTradeId = weChatTradeId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public WeChatTradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(WeChatTradeType tradeType) {
        this.tradeType = tradeType;
    }
}
