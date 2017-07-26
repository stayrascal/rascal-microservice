package com.stayrascal.cloud.user.auth.contract.command;

public class CreateRoleCommand {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
