package com.stayrascal.cloud.store.contract.client;

import com.stayrascal.cloud.common.contract.result.PageResult;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "service-store"
)
public interface LocationServiceClient {
    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/locations/{latitude}/{longitude}/stores"},
            consumes = {"application/json"}
    )
    PageResult list(@PathVariable("latitude") Double var1, @PathVariable("longitude") Double var2,
                    @RequestParam("distance") Integer var3, @RequestParam("province_id") Long var4,
                    @RequestParam("city_id") Long var5, @RequestParam("search_content") String var6,
                    @RequestParam("sort_type") String var7, @RequestParam("sort_by") String var8,
                    @RequestParam("page_size") Integer var9, @RequestParam("page_index") Integer var10);
}
