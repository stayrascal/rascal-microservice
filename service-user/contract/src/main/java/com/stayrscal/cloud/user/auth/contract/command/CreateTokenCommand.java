package com.stayrscal.cloud.user.auth.contract.command;

public class CreateTokenCommand {
    private String key;

    public CreateTokenCommand() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}