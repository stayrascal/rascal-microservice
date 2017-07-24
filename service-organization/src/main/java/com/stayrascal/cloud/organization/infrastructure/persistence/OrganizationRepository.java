package com.stayrascal.cloud.organization.infrastructure.persistence;

import com.exmertec.yaz.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class OrganizationRepository extends BaseDao<OrganizationPo> {

    @Autowired
    public OrganizationRepository(EntityManager entityManager) {
        super(entityManager, OrganizationPo.class);
    }

    public OrganizationPo ofId(String id) {
        return idEquals(id).querySingle();
    }
}
