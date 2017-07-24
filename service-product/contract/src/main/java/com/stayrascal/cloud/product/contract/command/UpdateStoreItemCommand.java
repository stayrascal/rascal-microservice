package com.stayrascal.cloud.product.contract.command;

public class UpdateStoreItemCommand {
    private Integer quantity;

    public UpdateStoreItemCommand() {
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
