package com.stayrascal.cloud.order.functional.resource;

import com.stayrascal.cloud.product.contract.client.StoreProductServiceClient;
import com.stayrascal.cloud.product.contract.command.CreateStoreProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateStoreItemQuantityCommand;
import com.stayrascal.cloud.product.contract.command.UpdateStoreProductCommand;
import com.stayrascal.cloud.product.contract.dto.StoreProductDto;
import com.stayrascal.cloud.product.contract.enumeration.ProductStatus;
import com.stayrascal.clould.common.contract.result.CreatedResult;
import com.stayrascal.clould.common.contract.result.PageResult;

import com.exmertec.dummie.Dummie;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Component
public class MockStoreProductServiceClient implements StoreProductServiceClient {
    private StoreProductDto storeProduct = Dummie.prepare(StoreProductDto.class)
            .override(BigDecimal.class, BigDecimal.ONE).build();

    @Override
    public StoreProductDto getStoreProduct(@PathVariable("id") String id) {
        return storeProduct;
    }

    @Override
    public PageResult listProductByStore(@RequestParam("category_id") String categoryId,
                                         @RequestParam("store_id") String storeId,
                                         @RequestParam("create_time_from") Long from,
                                         @RequestParam("create_time_to") Long to,
                                         @RequestParam("name") String name,
                                         @RequestParam("status") ProductStatus productStatus,
                                         @RequestParam("sort_type") String sortType,
                                         @RequestParam("sort_by") String sortBy,
                                         @RequestParam("page_size") Integer pageSize,
                                         @RequestParam("page_index") Integer pageIndex) {
        return null;
    }

    @Override
    public void updateProductItemQuantity(@PathVariable("id") String id,
                                          @PathVariable("item_id") String itemId,
                                          UpdateStoreItemQuantityCommand updateStoreItemQuantityCommand) {
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

    @Override
    public CreatedResult createStoreProduct(CreateStoreProductCommand createStoreProductCommand) {
        return null;
    }

    public void setStoreProduct(StoreProductDto storeProduct) {
        this.storeProduct = storeProduct;
    }
}
