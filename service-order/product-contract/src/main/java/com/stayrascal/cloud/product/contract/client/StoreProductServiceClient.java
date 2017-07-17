package com.stayrascal.cloud.product.contract.client;

import com.stayrascal.cloud.common.result.CreatedResult;
import com.stayrascal.cloud.common.result.PageResult;
import com.stayrascal.cloud.product.contract.command.CreateStoreProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateStoreItemQuantityCommand;
import com.stayrascal.cloud.product.contract.command.UpdateStoreProductCommand;
import com.stayrascal.cloud.product.contract.dto.StoreProductDto;
import com.stayrascal.cloud.product.contract.enumeration.ProductStatus;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "service-product"
)
public interface StoreProductServiceClient {
    @RequestMapping(
            method = {RequestMethod.POST},
            path = {"/rest/store-products"},
            consumes = {"application/json"}
    )
    CreatedResult createStoreProduct(CreateStoreProductCommand var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/store-products/{id}"},
            consumes = {"application/json"}
    )
    StoreProductDto getStoreProduct(@PathVariable("id") String var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/store-products"},
            consumes = {"application/json"}
    )
    PageResult listProductByStore(@RequestParam("category_id") String var1, @RequestParam("store_id") String var2, @RequestParam("create_time_from") Long var3, @RequestParam("create_time_to") Long var4, @RequestParam("name") String var5, @RequestParam("status") ProductStatus var6, @RequestParam("sort_type") String var7, @RequestParam("sort_by") String var8, @RequestParam("page_size") Integer var9, @RequestParam("page_index") Integer var10);

    @RequestMapping(
            method = {RequestMethod.PUT},
            path = {"/rest/store-products/{id}/items/{item_id}/quantity"},
            consumes = {"application/json"}
    )
    void updateProductItemQuantity(@PathVariable("id") String var1, @PathVariable("item_id") String var2, UpdateStoreItemQuantityCommand var3);

    @RequestMapping(
            method = {RequestMethod.PUT},
            path = {"/rest/store-products/{id}"},
            consumes = {"application/json"}
    )
    void updateStoreProduct(@PathVariable("id") String var1, UpdateStoreProductCommand var2);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/stores/{store_id}/categories/{category_id}/products"},
            consumes = {"application/json"}
    )
    PageResult listStoreProductsByCategory(@PathVariable("store_id") String var1, @PathVariable("category_id") String var2, @RequestParam("sort_type") String var3, @RequestParam("sort_by") String var4, @RequestParam("page_size") Integer var5, @RequestParam("page_index") Integer var6);
}
