package com.stayrascal.cloud.auth.contract.command;

import java.util.Date;

public class CreateAuthenticationKeyCommand {
    private String keyValue;
    private Date expiredTime;

    public CreateAuthenticationKeyCommand() {
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
