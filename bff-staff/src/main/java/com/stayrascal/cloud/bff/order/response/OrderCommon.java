package com.stayrascal.cloud.bff.order.response;

import com.stayrascal.cloud.order.contract.enumeration.DeliveryMethod;
import com.stayrascal.cloud.order.contract.enumeration.OrderStatus;
import com.stayrascal.cloud.order.contract.enumeration.TransactionProvider;

import java.math.BigDecimal;
import java.util.Date;

public class OrderCommon {
    private String id;
    private String userId;
    private String storeId;
    private DeliveryMethod deliveryMethod;
    private OrderStatus status;
    private Date placedTime;
    private Date timeCreated;
    private TransactionProvider transactionProvider;
    private String customerMobile;
    private String pickupCode;
    private BigDecimal totalCost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getPlacedTime() {
        return placedTime;
    }

    public void setPlacedTime(Date placedTime) {
        this.placedTime = placedTime;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public TransactionProvider getTransactionProvider() {
        return transactionProvider;
    }

    public void setTransactionProvider(TransactionProvider transactionProvider) {
        this.transactionProvider = transactionProvider;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getPickupCode() {
        return pickupCode;
    }

    public void setPickupCode(String pickupCode) {
        this.pickupCode = pickupCode;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
