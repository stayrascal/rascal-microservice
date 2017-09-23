package com.stayrascal.cloud.mapping.contract.command;

public class CreateStaffAddressMappingCommand {
    private String userId;
    private Long addressId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
