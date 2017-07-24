package com.stayrascal.cloud.store.infrastructure.persistence;

import com.exmertec.yaz.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class StoreRepository extends BaseDao<StorePo> {

    @Autowired
    public StoreRepository(EntityManager entityManager) {
        super(entityManager, StorePo.class);
    }

    public StorePo ofId(String id) {
        return idEquals(id).querySingle();
    }
}
