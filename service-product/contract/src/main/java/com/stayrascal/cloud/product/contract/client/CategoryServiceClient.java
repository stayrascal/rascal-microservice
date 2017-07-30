package com.stayrascal.cloud.product.contract.client;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.ListResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.product.contract.command.CreateCategoryCommand;
import com.stayrascal.cloud.product.contract.command.CreateProductOptionCommand;
import com.stayrascal.cloud.product.contract.command.UpdateCategoryCommand;
import com.stayrascal.cloud.product.contract.dto.CategoryDto;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(
        name = "service-product"
)
public interface CategoryServiceClient {
    @RequestMapping(
            method = {RequestMethod.POST},
            path = {"/rest/categories"},
            consumes = {"application/json"}
    )
    CreatedResult createCategory(CreateCategoryCommand var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/categories/{id}"},
            consumes = {"application/json"}
    )
    CategoryDto getCategory(@PathVariable("id") Long var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/categories"},
            consumes = {"application/json"}
    )
    PageResult listCategories(@RequestParam("sort_type") SortType var1, @RequestParam("sort_by") String var2,
                              @RequestParam("page_size") Integer var3, @RequestParam("page_index") Integer var4,
                              @RequestParam Map<String, String> var5);

    default PageResult listCategories(Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return this.listCategories(SortType.ASC, "timeCreated", pageSize, pageIndex, queryMap);
    }

    @RequestMapping(
            method = {RequestMethod.PUT},
            path = {"rest/categories/{id}"},
            consumes = {"application/json"}
    )
    void updateCategory(@PathVariable("id") Long var1, UpdateCategoryCommand var2);

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
    CreatedResult createOptions(@PathVariable("id") Long var1, CreateProductOptionCommand var2);
}
