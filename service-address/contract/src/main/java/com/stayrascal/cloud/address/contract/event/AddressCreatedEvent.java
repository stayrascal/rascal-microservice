package com.stayrascal.cloud.address.contract.event;

import com.stayrascal.cloud.common.contract.DomainEvent;

public class AddressCreatedEvent implements DomainEvent {
    private String id;

    public AddressCreatedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
