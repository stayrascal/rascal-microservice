package com.stayrascal.cloud.product.infrastructure.persistence;

import com.exmertec.yaz.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ProductRepository extends BaseDao<ProductPo> {

    @Autowired
    public ProductRepository(EntityManager entityManager) {
        super(entityManager, ProductPo.class);
    }

    public ProductPo ofId(String id) {
        return idEquals(id).querySingle();
    }
}
