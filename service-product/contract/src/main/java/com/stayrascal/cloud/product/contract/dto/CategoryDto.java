package com.stayrascal.cloud.product.contract.dto;

import java.util.List;

public class CategoryDto {
    private String id;
    private String name;
    private int index;
    private List<ProductOptionDto> options;

    public CategoryDto() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<ProductOptionDto> getOptions() {
        return this.options;
    }

    public void setOptions(List<ProductOptionDto> options) {
        this.options = options;
    }
}
