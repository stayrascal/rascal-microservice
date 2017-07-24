package com.stayrascal.cloud.store.infrastructure.factory;

import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;
import com.stayrascal.cloud.common.ddd.EventSender;
import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.store.contract.dto.StoreDto;
import com.stayrascal.cloud.store.domain.entity.Store;
import com.stayrascal.cloud.store.infrastructure.persistence.StorePo;
import com.stayrascal.cloud.store.infrastructure.persistence.StorePoMapper;
import com.stayrascal.cloud.store.infrastructure.persistence.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StoreFactory {
    private final UniqueKeyGenerator uniqueKeyGenerator;
    private final EventSender eventSender;
    private final StorePoMapper mapper;
    private final StoreRepository storeRepository;

    @Autowired
    public StoreFactory(UniqueKeyGenerator uniqueKeyGenerator,
                        StoreRepository storeRepository,
                        EventSender eventSender) {
        this.uniqueKeyGenerator = uniqueKeyGenerator;
        this.storeRepository = storeRepository;
        this.eventSender = eventSender;
        this.mapper = new StorePoMapper();
    }

    public Store createWithDto(StoreDto storeDto) {
        StorePo store = mapper.map(storeDto, StorePo.class);
        store.setId(uniqueKeyGenerator.generateKey());
        store.setStatus(CommonStatus.ENABLED);
        storeRepository.save(store);

        return toStore(store).get();
    }

    public Optional<Store> createWithId(String id) {
        return toStore(storeRepository.ofId(id));
    }

    private Optional<Store> toStore(StorePo storePo) {
        if (storePo == null) {
            return Optional.empty();
        }
        Store store = mapper.map(storePo, Store.class);
        store.setNotifyChange(entity -> storeRepository.update(mapper.map(entity, StorePo.class)));
        return Optional.of(store);
    }
}
