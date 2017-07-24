package com.stayrascal.cloud.product.contract.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class StoreProductItemDto {
    private String id;
    private String storeProductId;
    private String itemId;
    private String storeId;
    private Integer quantity;
    private BigDecimal price;
    private Date timeCreated;
    private List<OptionPair> optionPairs;

    public StoreProductItemDto() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreProductId() {
        return this.storeProductId;
    }

    public void setStoreProductId(String storeProductId) {
        this.storeProductId = storeProductId;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getTimeCreated() {
        return this.timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public List<OptionPair> getOptionPairs() {
        return this.optionPairs;
    }

    public void setOptionPairs(List<OptionPair> optionPairs) {
        this.optionPairs = optionPairs;
    }
}
