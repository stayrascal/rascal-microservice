package com.stayrascal.cloud.bff.store;

import com.stayrascal.cloud.address.contract.client.AddressServiceClient;
import com.stayrascal.cloud.bff.common.PageResponse;
import com.stayrascal.cloud.bff.store.response.StoreResponse;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.organization.contract.client.StoreServiceClient;
import com.stayrascal.cloud.organization.contract.dto.StoreDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StoreService {
    private static final Logger LOG = LoggerFactory.getLogger(StoreService.class);
    @Autowired
    private StoreServiceClient storeServiceClient;

    @Autowired
    private AddressServiceClient addressClient;

    private DefaultMapper mapper = DefaultMapper.builder().build();

    public PageResponse list(Long provinceId, Long cityId, Long fromDate, Long toDate, String name,
                             SortType sortType, String sortBy, Integer pageSize, Integer pageIndex) {
        LOG.info("try list store, filters: provinceId: {}, cityId: {}. fromDate: {}, toDate: {}, name: {}",
                provinceId, cityId, fromDate, toDate, name);

        PageResult result = storeServiceClient.listStores(sortType, sortBy, pageSize, pageIndex, provinceId, cityId,
                fromDate, toDate, name, null);
        List<StoreDto> stores = result.getItems();
        return new PageResponse(result.getTotalCount(), result.getPageSize(), result.getPageIndex(), stores
                .stream()
                .map(this::toStoreResponse)
                .collect(Collectors.toList()));
    }

    private StoreResponse toStoreResponse(StoreDto store) {
        StoreResponse response = mapper.map(store, StoreResponse.class);
        response.setProvince(addressClient.get(store.getLocation().getProvinceId()));
        response.setCity(addressClient.get(store.getLocation().getCityId()));
        response.setDistrict(addressClient.get(store.getLocation().getDistrictId()));

        return response;
    }

    public StoreResponse get(String id) {
        return toStoreResponse(storeServiceClient.getStore(id));
    }
}
