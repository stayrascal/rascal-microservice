package com.stayrascal.cloud.product.contract.dto;

import com.stayrascal.cloud.common.enumeration.CommonStatus;

import java.util.Date;
import java.util.List;

public class ProductDto {
    private String id;
    private CategoryDto category;
    private String name;
    private String description;
    private String thumbnail;
    private CommonStatus status;
    private List<ItemDto> items;
    private Date timeCreated;

    public ProductDto() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CategoryDto getCategory() {
        return this.category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<ItemDto> getItems() {
        return this.items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
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

    public CommonStatus getStatus() {
        return this.status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public Date getTimeCreated() {
        return this.timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
}
