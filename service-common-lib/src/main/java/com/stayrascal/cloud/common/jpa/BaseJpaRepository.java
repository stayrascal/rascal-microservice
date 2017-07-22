package com.stayrascal.cloud.common.jpa;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class BaseJpaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public BaseJpaRepository() {
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
