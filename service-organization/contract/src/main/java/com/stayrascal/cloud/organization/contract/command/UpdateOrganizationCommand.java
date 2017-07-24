package com.stayrascal.cloud.organization.contract.command;

public class UpdateOrganizationCommand {
    private String name;

    public UpdateOrganizationCommand() {
    }

    public UpdateOrganizationCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
