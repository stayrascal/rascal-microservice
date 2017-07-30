package com.stayrascal.cloud.product.infrastructure.persistence;

import static com.exmertec.yaz.BaseDao.field;
import static com.google.common.collect.Lists.newArrayList;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.product.domain.entity.Category;
import com.stayrascal.cloud.product.domain.mapper.CategoryPoMapper;
import com.stayrascal.cloud.product.domain.repository.CategoryRepository;
import com.stayrascal.cloud.product.infrastructure.persistence.po.CategoryPo;

import com.exmertec.yaz.BaseDao;
import com.exmertec.yaz.core.OrderType;
import com.exmertec.yaz.core.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

@Component
public class JpaCategoryRepository implements CategoryRepository {

    private final BaseDao<CategoryPo> categoryPoDao;
    private final CategoryPoMapper mapper;

    @Autowired
    public JpaCategoryRepository(EntityManager entityManager, CategoryPoMapper mapper) {
        this.categoryPoDao = new BaseDao(entityManager, CategoryPo.class);
        this.mapper = mapper;
    }

    @Override
    public Optional<Category> ofId(String id) {
        CategoryPo categoryPo = categoryPoDao.idEquals(id).querySingle();
        if (categoryPo == null) {
            return Optional.empty();
        }
        return Optional.of(mapper.categoryFromPo(categoryPo));
    }

    @Override
    public String insert(Category category) {
        CategoryPo categoryPo = mapper.categoryToPo(category);
        categoryPoDao.save(categoryPo);
        return categoryPo.getId();
    }

    @Override
    public Category update(Category category) {
        categoryPoDao.update(mapper.categoryToPo(category));
        return category;
    }

    @Override
    public List<Category> listCategories(SortQuery sortQuery, Map<String, String> queryMaps) {
        Query[] queries = generateListQueries(queryMaps);
        OrderType orderType = (sortQuery.getSortType() == SortType.ASC ? OrderType.ASCENDING : OrderType.DESCENDING);
        return categoryPoDao.where(queries).orderBy(orderType, sortQuery.getSortBy())
                .queryPage(sortQuery.getPageSize(), sortQuery.getPageIndex())
                .stream()
                .map(mapper::categoryFromPo)
                .collect(Collectors.toList());
    }

    private Query[] generateListQueries(Map<String, String> queryMaps) {
        List<Query> queries = newArrayList();
        for (Map.Entry<String, String> entry : queryMaps.entrySet()) {
            queries.add(field(entry.getKey()).eq(entry.getValue()));
        }
        return queries.toArray(new Query[0]);
    }
}
