package com.stayrascal.cloud.product.contract.client;

import com.stayrascal.cloud.product.contract.command.CreateProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateProductInfoCommand;
import com.stayrascal.cloud.product.contract.dto.ProductDto;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "service-product"
)
public interface ProductServiceClient {
    @RequestMapping(
            method = {RequestMethod.POST},
            path = {"/rest/products"},
            consumes = {"application/json"}
    )
    CreatedResult create(CreateProductCommand var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/products/{id}"},
            consumes = {"application/json"}
    )
    ProductDto get(@PathVariable("id") String var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/products"},
            consumes = {"application/json"}
    )
    PageResult list(@RequestParam("sort_type") String var1, @RequestParam("sort_by") String var2, @RequestParam("page_size") Integer var3, @RequestParam("page_index") Integer var4);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/products/catalogs"},
            consumes = {"application/json"}
    )
    PageResult list(@RequestParam("store_id") String var1, @RequestParam("category_id") String var2, @RequestParam("create_time_from") Long var3, @RequestParam("create_time_to") Long var4, @RequestParam("name") String var5, @RequestParam("filter_included") Boolean var6, @RequestParam("sort_type") SortType var7, @RequestParam("sort_by") String var8, @RequestParam("page_size") Integer var9, @RequestParam("page_index") Integer var10);

    @RequestMapping(
            method = {RequestMethod.PUT},
            path = {"/rest/products/{id}"},
            consumes = {"application/json"}
    )
    void update(@PathVariable("id") String var1, UpdateProductInfoCommand var2);
}
