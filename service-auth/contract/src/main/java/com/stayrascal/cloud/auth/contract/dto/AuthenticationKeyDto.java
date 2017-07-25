package com.stayrascal.cloud.auth.contract.dto;

import java.util.Date;

public class AuthenticationKeyDto {
    private String keyValue;
    private Date expiredTime;

    public AuthenticationKeyDto() {
    }

    public String getKeyValue() {
        return this.keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public Date getExpiredTime() {
        return this.expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }
}
