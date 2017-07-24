package com.stayrascal.cloud.address.infrastructure.persistence;

import com.stayrascal.cloud.address.domain.entity.Address;
import com.stayrascal.cloud.common.mapper.DefaultMapper;

public class AddressPoMapper extends DefaultMapper {
    public AddressPoMapper() {
        register(AddressPo.class, Address.class);
    }
}
