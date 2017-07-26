package com.stayrascal.cloud.bff.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignInRequest {
    private String accountId;
    private String password;

    public SignInRequest(@JsonProperty("accountId") String accountId,
                         @JsonProperty("password") String password) {
        this.accountId = accountId;
        this.password = password;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
