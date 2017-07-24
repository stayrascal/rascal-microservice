package com.stayrascal.cloud.organization.contract.client;

import com.stayrascal.cloud.common.contract.QueryMap;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.ListResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.organization.contract.command.CreateStoreCommand;
import com.stayrascal.cloud.organization.contract.command.UpdateStoreCommand;
import com.stayrascal.cloud.organization.contract.dto.StoreDto;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import javax.ws.rs.core.MediaType;

@FeignClient(name = "service-organization")
public interface StoreServiceClient {
    @RequestMapping(method = RequestMethod.POST, path = "/rest/stores",
            consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    CreatedResult createStore(CreateStoreCommand createCommand);

    @RequestMapping(method = RequestMethod.GET, path = "/rest/stores/{id}", consumes = MediaType.APPLICATION_JSON)
    StoreDto getStore(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, value = "/rest/stores", consumes = MediaType.APPLICATION_JSON)
    PageResult listStores(@RequestParam("sort_type") SortType sortType,
                          @RequestParam("sort_by") String sortBy,
                          @RequestParam("page_size") Integer pageSize,
                          @RequestParam("page_index") Integer pageIndex,
                          @RequestParam Map<String, String> queryMap);

    default PageResult listStores(Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return listStores(SortType.ASC, "timeCreated", pageSize, pageIndex, queryMap);
    }

    default PageResult listStores(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex,
                                  Long provinceId, Long cityId, Long fromDate, Long toDate, String name,
                                  String organizationId) {
        Map<String, String> queryMap = QueryMap.builder()
                .put("province_id", provinceId, String::valueOf)
                .put("city_id", cityId, String::valueOf)
                .put("from_date", fromDate, String::valueOf)
                .put("to_date", toDate, String::valueOf)
                .put("name", name)
                .put("organization_id", organizationId)
                .build();

        return listStores(sortType, sortBy, pageSize, pageIndex, queryMap);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/rest/stores/{id}",
            consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    void updateStore(@PathVariable("id") String id, UpdateStoreCommand updateCommand);

    @RequestMapping(method = RequestMethod.GET, path = "/rest/stores/cities", consumes = MediaType.APPLICATION_JSON)
    ListResult listCitiesHaveStore();
}
