package com.stayrascal.cloud.product.contract.dto;

import com.stayrascal.cloud.product.contract.enumeration.ProductStatus;

import java.util.Date;
import java.util.List;

public class StoreProductDto {
    private String id;
    private String productId;
    private String categoryId;
    private String storeId;
    private String name;
    private String description;
    private String thumbnail;
    private List<StoreItemDto> items;
    private ProductStatus status;
    private Date timeCreated;

    public StoreProductDto() {
    }

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public ProductStatus getStatus() {
        return this.status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Date getTimeCreated() {
        return this.timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<StoreItemDto> getItems() {
        return this.items;
    }

    public void setItems(List<StoreItemDto> items) {
        this.items = items;
    }
}
