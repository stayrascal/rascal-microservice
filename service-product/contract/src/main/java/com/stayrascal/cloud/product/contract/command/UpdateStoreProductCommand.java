package com.stayrascal.cloud.product.contract.command;

import com.stayrascal.cloud.product.contract.enumeration.StoreProductStatus;

import java.util.Map;

public class UpdateStoreProductCommand {
    private StoreProductStatus status;
    private Map<String, UpdateStoreProductItemCommand> updateItemCommands;

    public UpdateStoreProductCommand() {
    }

    public StoreProductStatus getStatus() {
        return this.status;
    }

    public void setStatus(StoreProductStatus status) {
        this.status = status;
    }

    public Map<String, UpdateStoreProductItemCommand> getUpdateItemCommands() {
        return updateItemCommands;
    }

    public void setUpdateItemCommands(Map<String, UpdateStoreProductItemCommand> updateItemCommands) {
        this.updateItemCommands = updateItemCommands;
    }
}
