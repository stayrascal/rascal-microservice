package com.stayrascal.cloud.product.contract.result;

import com.stayrascal.cloud.product.contract.dto.ProductDto;

public class ProductForManager extends ProductDto {
    private boolean inStore;

    public ProductForManager() {
    }

    public boolean isInStore() {
        return this.inStore;
    }

    public void setInStore(boolean inStore) {
        this.inStore = inStore;
    }
}
