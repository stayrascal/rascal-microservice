package com.stayrascal.cloud.product.contract.client;

import com.stayrascal.cloud.common.contract.QueryMap;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.product.contract.command.CreateProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateProductCommand;
import com.stayrascal.cloud.product.contract.dto.ProductDto;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(
        name = "service-product"
)
public interface ProductServiceClient {
    @RequestMapping(
            method = {RequestMethod.POST},
            path = {"/rest/products"},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    CreatedResult createProduct(CreateProductCommand var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/products/{id}"},
            produces = {"application/json"}
    )
    ProductDto getProduct(@PathVariable("id") String var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/products"},
            produces = {"application/json"}
    )
    PageResult listProducts(@RequestParam("sort_type") SortType var1, @RequestParam("sort_by") String var2,
                            @RequestParam("page_size") Integer var3, @RequestParam("page_index") Integer var4,
                            @RequestParam Map<String, String> var5);

    default PageResult listProducts(Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return this.listProducts(SortType.ASC, "timeCreated", pageSize, pageIndex, queryMap);
    }

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/products/catalogs"},
            produces = {"application/json"}
    )
    PageResult listProductCatalogs(@RequestParam("sort_type") SortType var1, @RequestParam("sort_by") String var2,
                                   @RequestParam("page_size") Integer var3, @RequestParam("page_index") Integer var4,
                                   @RequestParam Map<String, String> var5);

    default PageResult listProductCatalogs(Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return this.listProductCatalogs(SortType.ASC, "timeCreated", pageSize, pageIndex, queryMap);
    }

    default PageResult listProductCatalogs(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex,
                                           @RequestParam("store_id") String storeId, @RequestParam("category_id") String categoryId,
                                           @RequestParam("create_time_from") Long createTimeFrom, @RequestParam("create_time_to") Long createTimeTo,
                                           @RequestParam("name") String name, @RequestParam("filter_included") Boolean filterIncluded) {
        Map<String, String> queryMap = QueryMap.builder()
                .put("store_id", storeId)
                .put("category_id", categoryId)
                .put("create_time_from", createTimeFrom, String::valueOf)
                .put("create_time_to", createTimeTo, String::valueOf)
                .put("name", name)
                .put("filter_included", filterIncluded, String::valueOf)
                .build();
        return this.listProductCatalogs(sortType, sortBy, pageSize, pageIndex, queryMap);
    }

    @RequestMapping(
            method = {RequestMethod.PUT},
            path = {"/rest/products/{id}"},
            consumes = {"application/json"}
    )
    void updateProduct(@PathVariable("id") String var1, UpdateProductCommand var2);

    @RequestMapping(
            method = {RequestMethod.DELETE},
            path = {"/rest/products/{id}"},
            consumes = {"application/json"}
    )
    void deleteProduct(@PathVariable("id") String var1);
}
