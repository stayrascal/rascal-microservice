package com.stayrascal.cloud.product.contract.event;

import com.stayrascal.cloud.common.DomainEvent;

public class ProductCreatedEvent implements DomainEvent {
    private String id;

    public ProductCreatedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
