package com.stayrascal.cloud.product.contract.command;

public class CreateCategoryCommand {
    private String name;

    public CreateCategoryCommand() {
    }

    public CreateCategoryCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
