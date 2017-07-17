package com.stayrascal.cloud.common.ddd;

import com.stayrascal.clould.common.contract.DomainEvent;

public interface EventSender {
    void send(DomainEvent event);
}
