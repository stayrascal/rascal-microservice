package com.stayrascal.cloud.address.domain.mapper;

import com.stayrascal.cloud.address.contract.dto.AddressDto;
import com.stayrascal.cloud.address.domain.entity.Address;
import com.stayrascal.cloud.common.mapper.DefaultMapper;

import org.springframework.stereotype.Component;

@Component
public class AddressDtoMapper extends DefaultMapper {
    public AddressDtoMapper() {
        register(AddressDto.class, Address.class);
    }

    public AddressDto addressToDto(Address address) {
        return map(address, AddressDto.class);
    }
}
