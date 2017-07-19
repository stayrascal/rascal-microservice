package com.stayrscal.cloud.user.member.contract.event;

import com.stayrascal.clould.common.contract.DomainEvent;

public class MemberCreatedEvent implements DomainEvent {
    private String id;

    public MemberCreatedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
