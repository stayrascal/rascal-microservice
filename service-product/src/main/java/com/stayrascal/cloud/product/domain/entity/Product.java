package com.stayrascal.cloud.product.domain.entity;

import com.stayrascal.cloud.product.contract.dto.ProductDto;

import java.util.function.Consumer;

public class Product {
    private String id;

    private final ProductDtoMapper mapper;

    private Consumer<Product> notifyChange;

    public Product() {
        mapper = new ProductDtoMapper();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductDto toDto() {
        return mapper.map(this, ProductDto.class);
    }
}
