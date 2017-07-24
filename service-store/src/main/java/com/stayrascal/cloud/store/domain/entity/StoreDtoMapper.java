package com.stayrascal.cloud.store.domain.entity;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.store.contract.dto.StoreDto;

class StoreDtoMapper extends DefaultMapper {
    public StoreDtoMapper() {
        register(StoreDto.class, Store.class);
    }
}
