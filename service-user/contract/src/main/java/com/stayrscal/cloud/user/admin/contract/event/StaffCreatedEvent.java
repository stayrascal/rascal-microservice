package com.stayrscal.cloud.user.admin.contract.event;

import com.stayrascal.cloud.common.contract.DomainEvent;

public class StaffCreatedEvent implements DomainEvent {
    private String id;

    public StaffCreatedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
