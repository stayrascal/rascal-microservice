package com.stayrascal.cloud.store.infrastructure.persistence;

import static com.exmertec.yaz.BaseDao.field;
import static com.google.common.collect.Lists.newArrayList;

import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;
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

@Component
public class JpaStoreRepository implements StoreRepository {
    private final BaseDao<StorePo> storeDao;
    private final StorePoMapper storePoMapper;

    @Autowired
    public JpaStoreRepository(BaseDao<StorePo> storeDao, StorePoMapper storePoMapper) {
        this.storeDao = storeDao;
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
    public long countStores(String provinceId, String cityId, String districtId, CommonStatus status) {
        return storeDao.where(generateListQueries(provinceId, cityId, districtId, status)).count();
    }

    @Override
    public List<Store> listStores(SortQuery sortQuery, String provinceId, String cityId, String districtId, CommonStatus status) {
        Query[] queries = generateListQueries(provinceId, cityId, districtId, status);
        OrderType orderType = (sortQuery.getSortType() == SortType.ASC ? OrderType.ASCENDING : OrderType.DESCENDING);
        return storeDao.where(queries).orderBy(orderType, sortQuery.getSortBy())
                .queryPage(sortQuery.getPageSize(), sortQuery.getPageIndex())
                .stream()
                .map(storePoMapper::storeFromPo)
                .collect(Collectors.toList());
    }

    private Query[] generateListQueries(String provinceId, String cityId, String districtId, CommonStatus status) {
        List<Query> queries = newArrayList();
        if (!Strings.isNullOrEmpty(provinceId)) {
            queries.add(field("province_id").eq(provinceId));
        }
        if (!Strings.isNullOrEmpty(cityId)) {
            queries.add(field("city_id").eq(cityId));
        }
        if (!Strings.isNullOrEmpty(districtId)) {
            queries.add(field("district_id").eq(districtId));
        }
        if (status != null) {
            queries.add(field("status").eq(status));
        }
        return queries.toArray(new Query[0]);
    }
}
