package com.stayrascal.cloud.bff.functional.resource.mock;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.product.contract.client.StoreProductServiceClient;
import com.stayrascal.cloud.product.contract.command.CreateStoreProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateStoreItemCommand;
import com.stayrascal.cloud.product.contract.command.UpdateStoreProductCommand;
import com.stayrascal.cloud.product.contract.dto.StoreProductDto;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Component
public class MockStoreProductServiceClient implements StoreProductServiceClient {

    private String storeProductJson = "";


    public void setStoreProductJson(String storeProductJson) {
        this.storeProductJson = storeProductJson;
    }

    @Override
    public CreatedResult createStoreProduct(CreateStoreProductCommand var1) {
        return null;
    }

    @Override
    public StoreProductDto getStoreProduct(@PathVariable("id") String id) {
        return MockUtils.fromJson(storeProductJson, StoreProductDto.class);
    }

    @Override
    public PageResult listStoreProducts(SortType var1, String var2, Integer var3, Integer var4, Map<String, String> var5) {
        return null;
    }

    @Override
    public void updateStoreProductItem(String var1, String var2, UpdateStoreItemCommand var3) {

    }

    @Override
    public void updateStoreProduct(String var1, UpdateStoreProductCommand var2) {

    }

    @Override
    public PageResult listStoreProductsByCategory(String var1, String var2, String var3, String var4, Integer var5, Integer var6) {
        return null;
    }
}
