package com.stayrascal.cloud.store.domain.factory;

import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;
import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.store.contract.command.CreateStoreCommand;
import com.stayrascal.cloud.store.contract.dto.StoreDto;
import com.stayrascal.cloud.store.domain.entity.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreFactory {
    private final UniqueKeyGenerator keyGenerator;
    private final DefaultMapper storeMapper;

    @Autowired
    public StoreFactory(UniqueKeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
        this.storeMapper = DefaultMapper.builder()
                .register(CreateStoreCommand.class, Store.class)
                .build();
    }

    public StoreDto createOrder(CreateStoreCommand command) {
        StoreDto store = storeMapper.map(command, StoreDto.class);
        return complementCreatedStore(store);
    }

    private StoreDto complementCreatedStore(StoreDto store) {
        store.setId(keyGenerator.generateKey());
        store.setStatus(CommonStatus.ENABLED);
        return store;
    }
}
