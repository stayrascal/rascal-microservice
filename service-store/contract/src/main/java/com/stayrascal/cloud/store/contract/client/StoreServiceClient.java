package com.stayrascal.cloud.store.contract.client;

import com.stayrascal.cloud.common.contract.result.ListResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.store.contract.command.CreateStoreCommand;
import com.stayrascal.cloud.store.contract.command.UpdateStoreCommand;
import com.stayrascal.cloud.store.contract.dto.StoreDto;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "service-store"
)
public interface StoreServiceClient {

    @RequestMapping(
            method = {RequestMethod.POST},
            path = {"/rest/stores"},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    ResponseEntity create(CreateStoreCommand var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/stores/{id}"},
            consumes = {"application/json"}
    )
    StoreDto get(@PathVariable("id") String var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/stores"},
            consumes = {"application/json"}
    )
    PageResult list(@RequestParam("province_id") Long var1, @RequestParam("city_id") Long var2,
                    @RequestParam("from_date") Long var3, @RequestParam("to_date") Long var4,
                    @RequestParam("name") String var5, @RequestParam("sort_type") String var6,
                    @RequestParam("sort_by") String var7, @RequestParam("page_size") Integer var8,
                    @RequestParam("page_index") Integer var9);

    @RequestMapping(
            method = {RequestMethod.PUT},
            path = {"/rest/stores/{id}"},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    ResponseEntity update(@PathVariable("id") String var1, UpdateStoreCommand var2);

    @RequestMapping(
            method = {RequestMethod.DELETE},
            path = {"/rest/stores/{id}"},
            consumes = {"application/json"}
    )
    ResponseEntity delete(@PathVariable("id") String var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/stores/cities"},
            consumes = {"application/json"}
    )
    ListResult availableCities();
}
