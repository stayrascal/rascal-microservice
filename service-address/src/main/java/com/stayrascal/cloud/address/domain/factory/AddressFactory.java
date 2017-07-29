package com.stayrascal.cloud.address.domain.factory;

import com.stayrascal.cloud.address.contract.command.CreateAddressCommand;
import com.stayrascal.cloud.address.domain.entity.Address;
import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;
import com.stayrascal.cloud.common.mapper.DefaultMapper;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class AddressFactory {
    private final DefaultMapper addressDtoMapper;

    public AddressFactory() {
        this.addressDtoMapper = DefaultMapper.builder().register(CreateAddressCommand.class, Address.class).build();
    }

    public Address createAddress(CreateAddressCommand createAddressCommand) {
        Address address = addressDtoMapper.map(createAddressCommand, Address.class);
        address.setTimeCreated(DateTime.now().toDate());
        address.setStatus(CommonStatus.ENABLED);
        return address;
    }
}
