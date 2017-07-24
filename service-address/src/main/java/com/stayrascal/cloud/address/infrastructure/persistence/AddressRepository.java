package com.stayrascal.cloud.address.infrastructure.persistence;

import com.exmertec.yaz.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class AddressRepository extends BaseDao<AddressPo> {

    @Autowired
    public AddressRepository(EntityManager entityManager) {
        super(entityManager, AddressPo.class);
    }

    public AddressPo ofId(String id) {
        return idEquals(id).querySingle();
    }
}
