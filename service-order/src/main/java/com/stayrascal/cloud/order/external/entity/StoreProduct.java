package com.stayrascal.cloud.order.external.entity;

import com.stayrascal.cloud.product.contract.dto.StoreProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class StoreProduct {
    private String name;

    private List<StoreItem> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StoreItem> getItems() {
        return items;
    }

    public void setItems(List<StoreItem> items) {
        this.items = items;
    }

    public static StoreProduct mapFrom(StoreProductDto storeProductDto) {
        StoreProduct internalStoreProduct = new StoreProduct();
        List<StoreItem> items = storeProductDto.getItems().stream()
                .map(StoreItem::mapFrom).collect(Collectors.toList());
        internalStoreProduct.setItems(items);
        internalStoreProduct.setName(storeProductDto.getName());
        return internalStoreProduct;
    }
}
