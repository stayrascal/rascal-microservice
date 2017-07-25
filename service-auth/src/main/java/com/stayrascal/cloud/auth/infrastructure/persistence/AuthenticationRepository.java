package com.stayrascal.cloud.auth.infrastructure.persistence;

import com.exmertec.yaz.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class AuthenticationRepository extends BaseDao<AuthenticationPo> {

    @Autowired
    public AuthenticationRepository(EntityManager entityManager) {
        super(entityManager, AuthenticationPo.class);
    }

    public AuthenticationPo ofId(String id) {
        return idEquals(id).querySingle();
    }
}
