package com.stayrascal.cloud.store.infrastructure.persistence;

import static com.exmertec.yaz.BaseDao.field;
import static com.google.common.collect.Lists.newArrayList;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.store.domain.entity.Store;
import com.stayrascal.cloud.store.domain.mapper.StorePoMapper;
import com.stayrascal.cloud.store.domain.repository.StoreRepository;
import com.stayrascal.cloud.store.infrastructure.persistence.po.StorePo;

import com.exmertec.yaz.BaseDao;
import com.exmertec.yaz.core.OrderType;
import com.exmertec.yaz.core.Query;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

@Component
public class JpaStoreRepository implements StoreRepository {
    private final BaseDao<StorePo> storeDao;
    private final StorePoMapper storePoMapper;

    @Autowired
    public JpaStoreRepository(EntityManager entityManager, StorePoMapper storePoMapper) {
        this.storeDao = new BaseDao<>(entityManager, StorePo.class);
        this.storePoMapper = storePoMapper;
    }

    @Override
    public Optional<Store> ofId(String id) {
        StorePo storePo = storeDao.idEquals(id).querySingle();
        if (storePo == null) {
            return Optional.empty();
        }
        return Optional.of(storePoMapper.storeFromPo(storePo));
    }

    @Override
    public String insert(Store store) {
        StorePo storePo = storePoMapper.storeToPo(store);
        storeDao.save(storePo);
        return storePo.getId();
    }

    @Override
    public Store update(Store store) {
        storeDao.update(storePoMapper.storeToPo(store));
        return store;
    }

    @Override
    public long countStores(Long provinceId, Long cityId, String name) {
        return storeDao.where(generateListQueries(provinceId, cityId, name)).count();
    }

    @Override
    public List<Store> listStores(SortQuery sortQuery, Long provinceId, Long cityId, String name) {
        Query[] queries = generateListQueries(provinceId, cityId, name);
        OrderType orderType = (sortQuery.getSortType() == SortType.ASC ? OrderType.ASCENDING : OrderType.DESCENDING);
        return storeDao.where(queries).orderBy(orderType, sortQuery.getSortBy())
                .queryPage(sortQuery.getPageSize(), sortQuery.getPageIndex())
                .stream()
                .map(storePoMapper::storeFromPo)
                .collect(Collectors.toList());
    }

    private Query[] generateListQueries(Long provinceId, Long cityId, String name) {
        List<Query> queries = newArrayList();
        if (provinceId != null) {
            queries.add(field("province_id").eq(provinceId));
        }
        if (cityId != null) {
            queries.add(field("city_id").eq(cityId));
        }
        if (!Strings.isNullOrEmpty(name)) {
            queries.add(field("shop_date").like(name));
        }
        return queries.toArray(new Query[0]);
    }
}
