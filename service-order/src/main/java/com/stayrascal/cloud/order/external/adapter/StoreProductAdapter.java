package com.stayrascal.cloud.order.external.adapter;

import com.stayrascal.cloud.order.external.entity.StoreProduct;
import com.stayrascal.cloud.product.contract.client.StoreProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreProductAdapter {
    private final StoreProductServiceClient storeProductServiceClient;

    @Autowired
    public StoreProductAdapter(StoreProductServiceClient storeProductServiceClient) {
        this.storeProductServiceClient = storeProductServiceClient;
    }

    public StoreProduct getStoreProduct(String storeProductId) {
        return StoreProduct.mapFrom(storeProductServiceClient.getStoreProduct(storeProductId));
    }
}
