package com.stayrascal.cloud.bff.staff.response;

import com.stayrascal.cloud.user.admin.contract.dto.StaffDto;

public class ClerkResponse extends StaffDto {

    private String loginId;

    private String addressName;

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
