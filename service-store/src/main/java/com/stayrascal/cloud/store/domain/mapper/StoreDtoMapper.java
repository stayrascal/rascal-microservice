package com.stayrascal.cloud.store.domain.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.store.contract.dto.StoreDto;
import com.stayrascal.cloud.store.domain.entity.Store;

import org.springframework.stereotype.Component;

@Component
public class StoreDtoMapper extends DefaultMapper {
    public StoreDtoMapper() {
        register(StoreDto.class, Store.class);
    }

    public StoreDto storeToDto(Store store) {
        return map(store, StoreDto.class);
    }

    public Store storeFromDto(StoreDto storeDto) {
        return map(storeDto, Store.class);
    }
}
