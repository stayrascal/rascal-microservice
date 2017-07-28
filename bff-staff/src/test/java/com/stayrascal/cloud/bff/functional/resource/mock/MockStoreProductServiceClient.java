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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Component
public class MockStoreProductServiceClient implements StoreProductServiceClient {
    private String storeProductJson = "";

    public void setStoreProductJson(String storeProductJson) {
        this.storeProductJson = storeProductJson;
    }

    @Override
    public CreatedResult createStoreProduct(CreateStoreProductCommand createStoreProductCommand) {
        return null;
    }

    @Override
    public StoreProductDto getStoreProduct(@PathVariable("id") String id) {
        return MockUtils.fromJson(storeProductJson, StoreProductDto.class);
    }

    @Override
    public PageResult listStoreProducts(@RequestParam("sort_type") SortType sortType,
                                        @RequestParam("sort_by") String sortBy,
                                        @RequestParam("page_size") Integer pageSize,
                                        @RequestParam("page_index") Integer pageIndex,
                                        @RequestParam Map<String, String> queryMap) {
        return null;
    }

    @Override
    public void updateStoreProductItem(@PathVariable("id") String id,
                                       @PathVariable("item_id") String itemId,
                                       UpdateStoreItemCommand updateStoreItemCommand) {

    }

    @Override
    public void updateStoreProduct(@PathVariable("id") String id,
                                   UpdateStoreProductCommand updateStoreProductCommand) {
    }

    @Override
    public PageResult listStoreProductsByCategory(@PathVariable("store_id") String storeId,
                                                  @PathVariable("category_id") String categoryId,
                                                  @RequestParam("sort_type") String sortType,
                                                  @RequestParam("sort_by") String sortBy,
                                                  @RequestParam("page_size") Integer pageSize,
                                                  @RequestParam("page_index") Integer pageIndex) {
        return null;
    }
}
