package com.stayrascal.cloud.product.contract.command;

public class CreateStoreProductCommand {
    private String storeId;
    private String productId;

    public CreateStoreProductCommand() {
    }

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
