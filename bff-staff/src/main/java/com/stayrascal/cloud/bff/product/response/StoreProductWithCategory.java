package com.stayrascal.cloud.bff.product.response;

import com.stayrascal.cloud.product.contract.dto.StoreProductDto;

public class StoreProductWithCategory extends StoreProductDto {
    private String categoryName;

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
