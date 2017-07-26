package com.stayrascal.cloud.bff.functional.resource.mock;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.ListResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.organization.contract.client.StoreServiceClient;
import com.stayrascal.cloud.organization.contract.command.CreateStoreCommand;
import com.stayrascal.cloud.organization.contract.command.UpdateStoreCommand;
import com.stayrascal.cloud.organization.contract.dto.StoreDto;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Component
public class MockStoreClient implements StoreServiceClient {
    @Override
    public StoreDto getStore(@PathVariable("id") String id) {
        return null;
    }

    @Override
    public CreatedResult createStore(CreateStoreCommand createStoreCommand) {
        return new CreatedResult("id");
    }

    @Override
    public PageResult listStores(@RequestParam("sort_type") SortType sortType,
                                 @RequestParam("sort_by") String sortBy, @RequestParam("page_size") Integer pageSize,
                                 @RequestParam("page_index") Integer psageIndex, @RequestParam Map<String, String> queryMap) {
        return null;
    }

    @Override
    public PageResult listStores(Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return null;
    }

    @Override
    public PageResult listStores(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex, Long
            provinceId, Long cityId, Long fromDate, Long toDate, String name, String organizationId) {
        return null;
    }

    @Override
    public void updateStore(@PathVariable("id") String id, UpdateStoreCommand updateStoreCommand) {
    }

    @Override
    public ListResult listCitiesHaveStore() {
        return null;
    }
}
