package com.stayrascal.cloud.product.domain.repository;

import com.stayrascal.cloud.common.ddd.Repository;
import com.stayrascal.cloud.product.domain.entity.Product;

public interface ProductRepository extends Repository<Product, String> {
    void delete(Product product);
}
