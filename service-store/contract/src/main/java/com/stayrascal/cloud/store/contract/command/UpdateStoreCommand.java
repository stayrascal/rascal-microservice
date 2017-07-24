package com.stayrascal.cloud.store.contract.command;

public class UpdateStoreCommand {
    private String contactNumber;
    private String name;
    private String shopTime;
    private String closingTime;

    public UpdateStoreCommand() {
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopTime() {
        return this.shopTime;
    }

    public void setShopTime(String shopTime) {
        this.shopTime = shopTime;
    }

    public String getClosingTime() {
        return this.closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }
}
