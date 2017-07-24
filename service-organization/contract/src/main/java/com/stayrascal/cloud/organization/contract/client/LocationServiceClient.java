package com.stayrascal.cloud.organization.contract.client;

import com.stayrascal.cloud.common.contract.QueryMap;
import com.stayrascal.cloud.common.contract.result.ListResult;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import javax.ws.rs.core.MediaType;

@FeignClient(name = "service-organization")
public interface LocationServiceClient {
    @RequestMapping(method = RequestMethod.GET, path = "/rest/locations/{latitude}/{longitude}/stores",
            consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    ListResult listNearbyStores(@PathVariable("latitude") Double latitude,
                                @PathVariable("longitude") Double longitude,
                                @RequestParam("distance") Integer distance,
                                @RequestParam Map<String, String> queryMap);

    default ListResult listNearbyStores(Double latitude, Double longitude, Integer distance,
                                        Long provinceId, Long cityId, Long districtId, String textFilter) {
        Map<String, String> queryMap = QueryMap.builder()
                .put("province_id", provinceId, String::valueOf)
                .put("city_id", cityId, String::valueOf)
                .put("district_id", districtId, String::valueOf)
                .put("text_filter", textFilter)
                .build();

        return listNearbyStores(latitude, longitude, distance, queryMap);
    }
}
