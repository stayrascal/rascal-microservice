package com.stayrascal.cloud.store.domain.repository;

import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.ddd.Repository;
import com.stayrascal.cloud.store.domain.entity.Store;

import java.util.List;

public interface StoreRepository extends Repository<Store, String> {
    long countStores(String provinceId, String cityId, String districtId, CommonStatus status);

    List<Store> listStores(SortQuery sortQuery, String provinceId, String cityId, String districtId, CommonStatus status);
}
