package com.stayrascal.cloud.product.infrastructure.persistence;

import static com.exmertec.yaz.BaseDao.field;
import static com.google.common.collect.Lists.newArrayList;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.product.domain.entity.StoreProduct;
import com.stayrascal.cloud.product.domain.mapper.StoreProductPoMapper;
import com.stayrascal.cloud.product.domain.repository.StoreProductRepository;
import com.stayrascal.cloud.product.infrastructure.persistence.po.StoreProductPo;

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
public class JpaStoreProductRepository implements StoreProductRepository {
    private final BaseDao<StoreProductPo> storeProductPoDao;
    private final StoreProductPoMapper mapper;

    @Autowired
    public JpaStoreProductRepository(EntityManager entityManager, StoreProductPoMapper mapper) {
        this.storeProductPoDao = new BaseDao<>(entityManager, StoreProductPo.class);
        this.mapper = mapper;
    }

    @Override
    public Optional<StoreProduct> ofId(String id) {
        StoreProductPo storeProductPo = storeProductPoDao.idEquals(id).querySingle();
        return storeProductPo == null ? Optional.empty() : Optional.of(mapper.storeProductFromPo(storeProductPo));
    }

    @Override
    public String insert(StoreProduct storeProduct) {
        storeProductPoDao.save(mapper.storeProductToPo(storeProduct));
        return storeProduct.getId();
    }

    @Override
    public StoreProduct update(StoreProduct storeProduct) {
        return mapper.storeProductFromPo(storeProductPoDao.update(mapper.storeProductToPo(storeProduct)));
    }

    @Override
    public List<StoreProduct> listStoreProducts(SortQuery sortQuery, Map<String, String> queryMaps) {
        Query[] queries = generateListQueries(queryMaps);
        OrderType orderType = (sortQuery.getSortType() == SortType.ASC ? OrderType.ASCENDING : OrderType.DESCENDING);
        return storeProductPoDao.where(queries).orderBy(orderType, sortQuery.getSortBy())
                .queryPage(sortQuery.getPageSize(), sortQuery.getPageIndex())
                .stream()
                .map(mapper::storeProductFromPo)
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
