package com.stayrascal.cloud.common.ddd;

import com.stayrascal.cloud.common.DomainEvent;

public interface EventSender {
    void send(DomainEvent event);
}
