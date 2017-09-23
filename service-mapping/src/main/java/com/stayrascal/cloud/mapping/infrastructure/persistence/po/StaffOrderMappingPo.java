package com.stayrascal.cloud.mapping.infrastructure.persistence.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STAFF_ORDER_MAPPING")
public class StaffOrderMappingPo {
    @Id
    @Column(name = "user_id", length = 64, nullable = false, updatable = false)
    private String userId;

    @Column(name = "order_id", length = 64, nullable = false, updatable = false)
    private String orderId;

    public StaffOrderMappingPo() {
    }

    public StaffOrderMappingPo(String userId, String orderId) {
        this.userId = userId;
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
