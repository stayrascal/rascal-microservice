package com.stayrascal.cloud.product.contract.client;

import com.stayrascal.cloud.product.contract.command.CreateCategoryCommand;
import com.stayrascal.cloud.product.contract.command.CreateProductOptionCommand;
import com.stayrascal.cloud.product.contract.command.UpdateCategoryCommand;
import com.stayrascal.cloud.product.contract.dto.CategoryDto;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.ListResult;
import com.stayrascal.cloud.common.contract.result.PageResult;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "service-product"
)
public interface CategoryServiceClient {
    @RequestMapping(
            method = {RequestMethod.POST},
            path = {"/rest/categories"},
            consumes = {"application/json"}
    )
    CreatedResult create(CreateCategoryCommand var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/categories/{id}"},
            consumes = {"application/json"}
    )
    CategoryDto get(@PathVariable("id") String var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/categories"},
            consumes = {"application/json"}
    )
    PageResult list(@RequestParam("sort_type") String var1, @RequestParam("sort_by") String var2, @RequestParam("page_size") Integer var3, @RequestParam("page_index") Integer var4);

    @RequestMapping(
            method = {RequestMethod.PUT},
            path = {"rest/categories"},
            consumes = {"application/json"}
    )
    void update(UpdateCategoryCommand var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/stores/{store_id}/categories"},
            consumes = {"application/json"}
    )
    ListResult listStoreCategories(@PathVariable("store_id") String var1);

    @RequestMapping(
            method = {RequestMethod.POST},
            path = {"/rest/categories/{id}/options"},
            consumes = {"application/json"}
    )
    CreatedResult createOptions(@PathVariable("id") String var1, CreateProductOptionCommand var2);
}
