package com.stayrascal.cloud.product.contract.command;

public class UpdateStoreItemQuantityCommand {
    private Integer quantity;

    public UpdateStoreItemQuantityCommand() {
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
