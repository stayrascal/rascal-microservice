package com.stayrascal.cloud.user.auth.domain.entity;

import java.util.Date;

public class AuthenticationKey {
    private Long id;

    private String keyValue;

    private Date expiredTime;

    public AuthenticationKey() {
    }

    public AuthenticationKey(String keyValue, Date expiredTime) {
        this.keyValue = keyValue;
        this.expiredTime = expiredTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
