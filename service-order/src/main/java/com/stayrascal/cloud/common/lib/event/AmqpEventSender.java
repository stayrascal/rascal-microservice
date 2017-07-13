package com.stayrascal.cloud.common.lib.event;

import com.stayrascal.cloud.common.DomainEvent;
import com.stayrascal.cloud.common.lib.ddd.EventSender;
import org.springframework.stereotype.Component;

@Component
public class AmqpEventSender implements EventSender {

    public AmqpEventSender() {
    }

    @Override
    public void send(DomainEvent event) {

    }
}
