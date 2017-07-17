package com.stayrascal.cloud.order.infrastructure.persistence.po;

import com.stayrascal.cloud.common.jpa.BasePo;
import com.stayrascal.cloud.order.contract.enumeration.DeliveryMethod;
import com.stayrascal.cloud.order.contract.enumeration.OrderStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "[ORDER]")
public class OrderPo extends BasePo {
    @Column(name = "user_id", length = 64, updatable = false, nullable = false)
    private String userId;

    @Column(name = "store_id", length = 64, updatable = false, nullable = false)
    private String storeId;

    @Column(name = "note")
    private String note;

    @Column(name = "transaction_id", length = 64)
    private String transactionId;

    @Column(name = "delivery_method", nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private OrderStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderItemPo> items;

    @Column(name = "placed_time")
    private Date placedTime;

    @Column(name = "pickup_code", length = 32)
    private String pickupCode;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public List<OrderItemPo> getItems() {
        return items;
    }

    public void setItems(List<OrderItemPo> items) {
        this.items = items;
    }

    public Date getPlacedTime() {
        return placedTime;
    }

    public void setPlacedTime(Date placedTime) {
        this.placedTime = placedTime;
    }

    public String getPickupCode() {
        return pickupCode;
    }

    public void setPickupCode(String pickupCode) {
        this.pickupCode = pickupCode;
    }
}
