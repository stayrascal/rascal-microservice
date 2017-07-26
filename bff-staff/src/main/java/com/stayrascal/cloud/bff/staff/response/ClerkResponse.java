package com.stayrascal.cloud.bff.staff.response;

import com.stayrscal.cloud.user.admin.contract.dto.StaffDto;

public class ClerkResponse extends StaffDto {

    private String loginId;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
