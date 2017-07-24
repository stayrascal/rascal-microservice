package com.stayrascal.cloud.address.infrastructure.factory;

import com.stayrascal.cloud.address.contract.dto.AddressDto;
import com.stayrascal.cloud.address.domain.entity.Address;
import com.stayrascal.cloud.address.infrastructure.persistence.AddressPo;
import com.stayrascal.cloud.address.infrastructure.persistence.AddressPoMapper;
import com.stayrascal.cloud.address.infrastructure.persistence.AddressRepository;
import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;
import com.stayrascal.cloud.common.ddd.EventSender;
import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressFactory {
    private final UniqueKeyGenerator uniqueKeyGenerator;
    private final EventSender eventSender;
    private final AddressPoMapper mapper;
    private final AddressRepository addressRepository;

    @Autowired
    public AddressFactory(UniqueKeyGenerator uniqueKeyGenerator,
                          AddressRepository addressRepository,
                          EventSender eventSender) {
        this.uniqueKeyGenerator = uniqueKeyGenerator;
        this.addressRepository = addressRepository;
        this.eventSender = eventSender;
        this.mapper = new AddressPoMapper();
    }

    public Address createWithDto(AddressDto addressDto) {
        AddressPo address = mapper.map(addressDto, AddressPo.class);
        address.setId(uniqueKeyGenerator.generateKey());
        address.setStatus(CommonStatus.ENABLED);
        addressRepository.save(address);

        return toAddress(address).get();
    }

    public Optional<Address> createWithId(String id) {
        return toAddress(addressRepository.ofId(id));
    }

    private Optional<Address> toAddress(AddressPo addressPo) {
        if (addressPo == null) {
            return Optional.empty();
        }
        Address address = mapper.map(addressPo, Address.class);
        address.setNotifyChange(entity -> addressRepository.update(mapper.map(entity, AddressPo.class)));
        return Optional.of(address);
    }
}
