package com.stayrascal.cloud.order.domain.entity;

import java.math.BigDecimal;

public class OrderItem {
    private Long id;

    private String storeProductId;

    private String storeItemId;

    private String storeProductName;

    private String storeItemName;

    private Integer quantity;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreProductId() {
        return storeProductId;
    }

    public void setStoreProductId(String storeProductId) {
        this.storeProductId = storeProductId;
    }

    public String getStoreItemId() {
        return storeItemId;
    }

    public void setStoreItemId(String storeItemId) {
        this.storeItemId = storeItemId;
    }

    public String getStoreProductName() {
        return storeProductName;
    }

    public void setStoreProductName(String storeProductName) {
        this.storeProductName = storeProductName;
    }

    public String getStoreItemName() {
        return storeItemName;
    }

    public void setStoreItemName(String storeItemName) {
        this.storeItemName = storeItemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
