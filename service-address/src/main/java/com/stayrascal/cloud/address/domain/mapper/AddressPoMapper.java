package com.stayrascal.cloud.address.domain.mapper;

import com.stayrascal.cloud.address.domain.entity.Address;
import com.stayrascal.cloud.address.infrastructure.persistence.po.AddressPo;
import com.stayrascal.cloud.common.mapper.DefaultMapper;

import org.springframework.stereotype.Component;

@Component
public class AddressPoMapper extends DefaultMapper {
    public AddressPoMapper() {
        register(AddressPo.class, Address.class);
    }

    public Address addressFromPo(AddressPo addressPo) {
        return map(addressPo, Address.class);
    }

    public AddressPo addressToPo(Address address) {
        return map(address, AddressPo.class);
    }
}
