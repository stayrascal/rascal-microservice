package com.stayrascal.cloud.mapping.contract.event;

import com.stayrascal.cloud.common.contract.DomainEvent;

public class MappingCreatedEvent implements DomainEvent {
    private String userId;
    private String mappingId;
    private String mappingType;

    public MappingCreatedEvent(String userId, String mappingId, String mappingType) {
        this.userId = userId;
        this.mappingId = mappingId;
        this.mappingType = mappingType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMappingId() {
        return mappingId;
    }

    public void setMappingId(String mappingId) {
        this.mappingId = mappingId;
    }

    public String getMappingType() {
        return mappingType;
    }

    public void setMappingType(String mappingType) {
        this.mappingType = mappingType;
    }
}
