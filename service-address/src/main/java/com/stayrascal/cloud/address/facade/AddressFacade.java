package com.stayrascal.cloud.address.facade;

import com.stayrascal.cloud.address.contract.command.CreateAddressCommand;
import com.stayrascal.cloud.address.contract.command.UpdateAddressCommand;
import com.stayrascal.cloud.address.contract.dto.AddressDto;
import com.stayrascal.cloud.address.domain.entity.Address;
import com.stayrascal.cloud.address.domain.factory.AddressFactory;
import com.stayrascal.cloud.address.domain.mapper.AddressDtoMapper;
import com.stayrascal.cloud.address.infrastructure.persistence.JpaAddressRepository;
import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AddressFacade {

    private final JpaAddressRepository jpaAddressRepository;
    private final AddressFactory addressFactory;
    private final AddressDtoMapper addressDtoMapper;

    @Autowired
    public AddressFacade(JpaAddressRepository jpaAddressRepository, AddressFactory addressFactory, AddressDtoMapper addressDtoMapper) {
        this.jpaAddressRepository = jpaAddressRepository;
        this.addressFactory = addressFactory;
        this.addressDtoMapper = addressDtoMapper;
    }

    public AddressDto getAddressById(Long id) {
        return addressDtoMapper.addressToDto(getAddress(id));
    }

    private Address getAddress(Long id) {
        return jpaAddressRepository.ofId(id).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Address of id {} not found", id)
        );
    }

    public Long createAddress(CreateAddressCommand createAddressCommand) {
        Address address = addressFactory.createAddress(createAddressCommand);
        return jpaAddressRepository.insert(address);
    }

    public AddressDto updateAddressInfo(Long addressId, UpdateAddressCommand command) {
        Address address = getAddress(addressId);
        if (!Strings.isNullOrEmpty(command.getName())) {
            address.setName(command.getName());
        }
        if (!Strings.isNullOrEmpty(command.getLanguage())) {
            address.setLanguage(command.getLanguage());
        }
        if (!Strings.isNullOrEmpty(command.getNameEn())) {
            address.setNameEn(command.getNameEn());
        }
        if (!Strings.isNullOrEmpty(command.getPath())) {
            address.setPath(command.getPath());
        }
        if (command.getGrade() != null) {
            address.setGrade(command.getGrade());
        }
        if (command.getParentId() != null) {
            address.setParentId(command.getParentId());
        }
        jpaAddressRepository.update(address);
        return addressDtoMapper.addressToDto(address);
    }


}
