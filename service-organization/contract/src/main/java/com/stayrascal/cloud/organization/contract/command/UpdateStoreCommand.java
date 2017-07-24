package com.stayrascal.cloud.organization.contract.command;

public class UpdateStoreCommand {
    private String contactNumber;

    private String name;

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
