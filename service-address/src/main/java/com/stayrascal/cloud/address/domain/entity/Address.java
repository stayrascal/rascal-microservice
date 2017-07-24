package com.stayrascal.cloud.address.domain.entity;

import com.stayrascal.cloud.address.contract.dto.AddressDto;
import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;

import java.util.function.Consumer;

public class Address {
    private String id;

    private CommonStatus status;

    private final AddressDtoMapper mapper;

    private Consumer<Address> notifyChange;

    public Address() {
        mapper = new AddressDtoMapper();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public void setNotifyChange(Consumer<Address> notifyChange) {
        this.notifyChange = notifyChange;
    }

    public void updateAddressInfo(AddressDto addressDto) {
        notifyChange.accept(this);
    }

    public AddressDto toDto() {
        return mapper.map(this, AddressDto.class);
    }
}
