package com.stayrascal.cloud.address.contract.command;

import com.stayrascal.cloud.address.contract.dto.AddressDto;

public class UpdateAddressCommand {
    private AddressDto address;

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
