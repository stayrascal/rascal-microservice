package com.stayrascal.cloud.address.domain.entity;

import com.stayrascal.cloud.address.contract.dto.AddressDto;
import com.stayrascal.cloud.common.mapper.DefaultMapper;

class AddressDtoMapper extends DefaultMapper {
    public AddressDtoMapper() {
        register(AddressDto.class, Address.class);
    }
}
