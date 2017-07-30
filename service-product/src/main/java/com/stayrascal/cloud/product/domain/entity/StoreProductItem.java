package com.stayrascal.cloud.product.domain.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class StoreProductItem {
    private String id;
    private String storeProductId;
    private String itemId;
    private String storeId;
    private Integer quantity;
    private BigDecimal price;
    private Date timeCreated;
    private List<OptionPair> optionPairs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreProductId() {
        return storeProductId;
    }

    public void setStoreProductId(String storeProductId) {
        this.storeProductId = storeProductId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
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

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public List<OptionPair> getOptionPairs() {
        return optionPairs;
    }

    public void setOptionPairs(List<OptionPair> optionPairs) {
        this.optionPairs = optionPairs;
    }
}
