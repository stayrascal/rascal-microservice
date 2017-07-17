package com.stayrascal.cloud.common.event;

import com.stayrascal.cloud.common.DomainEvent;
import com.stayrascal.cloud.common.ddd.EventSender;
import org.springframework.stereotype.Component;

@Component
public class AmqpEventSender implements EventSender {

    public AmqpEventSender() {
    }

    @Override
    public void send(DomainEvent event) {

    }
}
