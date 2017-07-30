package com.stayrascal.cloud.product.domain.entity;

import com.stayrascal.cloud.product.contract.enumeration.StoreProductStatus;

import java.util.Date;
import java.util.List;

public class StoreProduct {
    private String id;
    private String productId;
    private String categoryId;
    private String storeId;
    private String name;
    private String description;
    private String thumbnail;
    private List<StoreProductItem> items;
    private StoreProductStatus status;
    private Date timeCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<StoreProductItem> getItems() {
        return items;
    }

    public void setItems(List<StoreProductItem> items) {
        this.items = items;
    }

    public StoreProductStatus getStatus() {
        return status;
    }

    public void setStatus(StoreProductStatus status) {
        this.status = status;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
}
