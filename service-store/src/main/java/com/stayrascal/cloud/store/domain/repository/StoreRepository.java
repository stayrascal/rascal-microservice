package com.stayrascal.cloud.store.domain.repository;

import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.ddd.Repository;
import com.stayrascal.cloud.store.domain.entity.Store;

import java.util.List;

public interface StoreRepository extends Repository<Store, String> {
    long countStores(Long provinceId, Long cityId, String name);

    List<Store> listStores(SortQuery sortQuery, Long provinceId, Long cityId, String name);
}
