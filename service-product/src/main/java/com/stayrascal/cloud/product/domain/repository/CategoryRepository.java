package com.stayrascal.cloud.product.domain.repository;

import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.ddd.Repository;
import com.stayrascal.cloud.product.domain.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryRepository extends Repository<Category, String> {
    List<Category> listCategories(SortQuery sortQuery, Map<String, String> queryMaps);
}
