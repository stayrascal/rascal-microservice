package com.stayrascal.cloud.product.domain.entity;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.contract.dto.ProductDto;

class ProductDtoMapper extends DefaultMapper {
    public ProductDtoMapper() {
        register(ProductDto.class, Product.class);
    }
}
