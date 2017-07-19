package com.stayrscal.cloud.user.auth.contract.command;

import java.util.Date;

public class CreateAuthenticationKeyCommand {
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
