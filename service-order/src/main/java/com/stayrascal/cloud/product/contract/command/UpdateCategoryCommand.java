package com.stayrascal.cloud.product.contract.command;

public class UpdateCategoryCommand {
    private String name;
    private Integer index;

    public UpdateCategoryCommand() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return this.index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
