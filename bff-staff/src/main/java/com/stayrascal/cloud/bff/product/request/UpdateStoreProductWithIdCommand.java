package com.stayrascal.cloud.bff.product.request;

import com.stayrascal.cloud.product.contract.command.UpdateStoreProductCommand;

public class UpdateStoreProductWithIdCommand extends UpdateStoreProductCommand {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
