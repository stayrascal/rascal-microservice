package com.stayrascal.cloud.store.domain.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.store.domain.entity.Store;
import com.stayrascal.cloud.store.infrastructure.persistence.po.StorePo;

public class StorePoMapper extends DefaultMapper {
    public StorePoMapper() {
        register(StorePo.class, Store.class);
    }

    public Store storeFromPo(StorePo storePo) {
        return map(storePo, Store.class);
    }


    public StorePo storeToPo(Store store) {
        return map(store, StorePo.class);
    }
}
