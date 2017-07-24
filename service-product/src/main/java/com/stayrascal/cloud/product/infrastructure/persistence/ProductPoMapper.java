package com.stayrascal.cloud.product.infrastructure.persistence;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.domain.entity.Product;

public class ProductPoMapper extends DefaultMapper {
    public ProductPoMapper() {
        register(ProductPo.class, Product.class);
    }
}
