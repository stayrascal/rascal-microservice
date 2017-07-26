package com.stayrascal.cloud.bff.product.response;

import com.stayrascal.cloud.product.contract.dto.ProductDto;

public class ProductResponse extends ProductDto {
    private Boolean referenceByStoreProduct;

    public void setReferenceByStoreProduct(Boolean referenceByStoreProduct) {
        this.referenceByStoreProduct = referenceByStoreProduct;
    }

    public Boolean getReferenceByStoreProduct() {
        return referenceByStoreProduct;
    }
}
