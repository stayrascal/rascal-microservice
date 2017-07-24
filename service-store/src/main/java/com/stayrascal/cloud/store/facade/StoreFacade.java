package com.stayrascal.cloud.store.facade;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.ddd.EventSender;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;
import com.stayrascal.cloud.store.contract.command.CreateStoreCommand;
import com.stayrascal.cloud.store.contract.command.UpdateStoreCommand;
import com.stayrascal.cloud.store.contract.dto.StoreDto;
import com.stayrascal.cloud.store.domain.entity.Store;
import com.stayrascal.cloud.store.infrastructure.factory.StoreFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class StoreFacade {
    private final StoreFactory storeFactory;
    private final EventSender eventSender;

    @Autowired
    public StoreFacade(StoreFactory storeFactory,
                       EventSender eventSender) {
        this.storeFactory = storeFactory;
        this.eventSender = eventSender;
    }

    public StoreDto getStoreById(String id) {
        return storeFactory.createWithId(id).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Store of id {} not found", id)
        ).toDto();
    }

    public String createStore(CreateStoreCommand createStoreCommand) {
        return createStoreCommand.getName();
    }

    public void updateStore(String storeId, UpdateStoreCommand command) {
        Store store = storeFactory.createWithId(storeId).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Store of id {} not found", storeId));
        store.setId(command.getName());
    }
}
