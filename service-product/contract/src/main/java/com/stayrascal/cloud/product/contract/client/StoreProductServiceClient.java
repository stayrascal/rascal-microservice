package com.stayrascal.cloud.product.contract.client;

import com.stayrascal.cloud.common.contract.QueryMap;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.product.contract.command.CreateStoreProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateStoreItemCommand;
import com.stayrascal.cloud.product.contract.command.UpdateStoreProductCommand;
import com.stayrascal.cloud.product.contract.dto.StoreProductDto;
import com.stayrascal.cloud.product.contract.enumeration.StoreProductStatus;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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
    PageResult listStoreProducts(@RequestParam("sort_type") SortType var1, @RequestParam("sort_by") String var2,
                                 @RequestParam("page_size") Integer var3, @RequestParam("page_index") Integer var4,
                                 @RequestParam Map<String, String> var5);

    default PageResult listStoreProducts(Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return this.listStoreProducts(SortType.ASC, "timeCreated", pageSize, pageIndex, queryMap);
    }

    default PageResult listStoreProducts(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex, String categoryId,
                                         String storeId, Long createTimeFrom, Long createTimeTo, String name, StoreProductStatus status) {
        Map<String, String> queryMap = QueryMap.builder()
                .put("category_id", categoryId)
                .put("store_id", storeId)
                .put("create_time_from", createTimeFrom, String::valueOf)
                .put("create_time_to", createTimeTo, String::valueOf)
                .put("name", name)
                .put("status", status, Enum::name)
                .build();
        return this.listStoreProducts(sortType, sortBy, pageSize, pageIndex, queryMap);
    }

    @RequestMapping(
            method = {RequestMethod.PUT},
            path = {"/rest/store-products/{id}/items/{item_id}"},
            consumes = {"application/json"}
    )
    void updateStoreProductItem(@PathVariable("id") String var1, @PathVariable("item_id") String var2, UpdateStoreItemCommand var3);

    @RequestMapping(
            method = {RequestMethod.PUT},
            path = {"/rest/store-products/{id}"},
            consumes = {"application/json"}
    )
    void updateStoreProduct(@PathVariable("id") String var1, UpdateStoreProductCommand var2);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/store-products/{store_id}/categories/{category_id}/products"},
            consumes = {"application/json"}
    )
    PageResult listStoreProductsByCategory(@PathVariable("store_id") String var1, @PathVariable("category_id") String var2,
                                           @RequestParam("sort_type") String var3, @RequestParam("sort_by") String var4,
                                           @RequestParam("page_size") Integer var5, @RequestParam("page_index") Integer var6);
}
