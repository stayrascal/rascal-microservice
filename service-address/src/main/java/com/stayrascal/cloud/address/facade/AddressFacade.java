package com.stayrascal.cloud.address.facade;

import com.stayrascal.cloud.address.contract.command.CreateAddressCommand;
import com.stayrascal.cloud.address.contract.command.UpdateAddressCommand;
import com.stayrascal.cloud.address.contract.dto.AddressDto;
import com.stayrascal.cloud.address.contract.event.AddressCreatedEvent;
import com.stayrascal.cloud.address.domain.entity.Address;
import com.stayrascal.cloud.address.infrastructure.factory.AddressFactory;
import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.ddd.EventSender;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AddressFacade {
    private final AddressFactory addressFactory;
    private final EventSender eventSender;

    @Autowired
    public AddressFacade(AddressFactory addressFactory,
                         EventSender eventSender) {
        this.addressFactory = addressFactory;
        this.eventSender = eventSender;
    }

    public AddressDto getAddressById(String id) {
        return addressFactory.createWithId(id).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Address of id {} not found", id)
        ).toDto();
    }

    public String createAddress(CreateAddressCommand createAddressCommand) {
        Address address = addressFactory.createWithDto(createAddressCommand.getAddress());

        AddressCreatedEvent addressCreatedEvent = new AddressCreatedEvent(address.getId());
        eventSender.send(addressCreatedEvent);

        return address.getId();
    }

    public void updateAddressInfo(String addressId, UpdateAddressCommand command) {
        Address address = addressFactory.createWithId(addressId).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Address of id {} not found", addressId));
        address.updateAddressInfo(command.getAddress());
    }
}
