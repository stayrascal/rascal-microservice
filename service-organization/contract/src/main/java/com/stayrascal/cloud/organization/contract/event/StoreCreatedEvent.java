package com.stayrascal.cloud.organization.contract.event;

import com.stayrascal.cloud.common.contract.DomainEvent;

public class StoreCreatedEvent implements DomainEvent {
    private String id;

    public StoreCreatedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
