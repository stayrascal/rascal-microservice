package com.stayrascal.cloud.product.contract.command;

import com.stayrascal.cloud.product.contract.enumeration.ProductStatus;

public class UpdateStoreProductCommand {
    private ProductStatus status;

    public UpdateStoreProductCommand() {
    }

    public ProductStatus getStatus() {
        return this.status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
