package com.stayrascal.cloud.mapping.contract.dto;

public class StaffAddressMappingDto {
    private String userId;
    private String addressId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
