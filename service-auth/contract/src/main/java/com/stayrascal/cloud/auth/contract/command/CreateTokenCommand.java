package com.stayrascal.cloud.auth.contract.command;

public class CreateTokenCommand {
    private String key;

    public CreateTokenCommand() {
    }

    public CreateTokenCommand(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
