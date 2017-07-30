package com.stayrascal.cloud.product.domain.repository;

import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.ddd.Repository;
import com.stayrascal.cloud.product.domain.entity.StoreProduct;

import java.util.List;
import java.util.Map;

public interface StoreProductRepository extends Repository<StoreProduct, String> {
    List<StoreProduct> listStoreProducts(SortQuery sortQuery, Map<String, String> queryMaps);
}
