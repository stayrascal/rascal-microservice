package com.stayrascal.cloud.common.lib.ddd;

import com.stayrascal.cloud.common.DomainEvent;

public interface EventSender {
    void send(DomainEvent event);
}
