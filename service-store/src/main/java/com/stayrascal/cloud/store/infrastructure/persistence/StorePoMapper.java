package com.stayrascal.cloud.store.infrastructure.persistence;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.store.domain.entity.Store;

public class StorePoMapper extends DefaultMapper {
    public StorePoMapper() {
        register(StorePo.class, Store.class);
    }
}
