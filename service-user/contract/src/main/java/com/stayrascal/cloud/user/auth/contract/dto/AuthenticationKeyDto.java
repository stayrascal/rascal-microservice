package com.stayrascal.cloud.user.auth.contract.dto;

import java.util.Date;

public class AuthenticationKeyDto {
    private String keyValue;

    private Date expiredTime;

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }
}
