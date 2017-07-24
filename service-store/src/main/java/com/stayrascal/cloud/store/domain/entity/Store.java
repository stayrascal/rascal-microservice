package com.stayrascal.cloud.store.domain.entity;

import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;
import com.stayrascal.cloud.store.contract.dto.StoreDto;

import java.util.function.Consumer;

public class Store {
    private String id;

    private CommonStatus status;

    private final StoreDtoMapper mapper;

    private Consumer<Store> notifyChange;

    public Store() {
        mapper = new StoreDtoMapper();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public void setNotifyChange(Consumer<Store> notifyChange) {
        this.notifyChange = notifyChange;
    }

    public void updateStore(StoreDto store) {
        notifyChange.accept(this);
    }

    public StoreDto toDto() {
        return mapper.map(this, StoreDto.class);
    }
}
