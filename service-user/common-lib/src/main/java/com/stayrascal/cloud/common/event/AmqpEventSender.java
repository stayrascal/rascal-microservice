package com.stayrascal.cloud.common.event;

import com.stayrascal.cloud.common.ddd.EventSender;
import com.stayrascal.clould.common.contract.DomainEvent;
import org.springframework.stereotype.Component;

@Component
public class AmqpEventSender implements EventSender {

    public AmqpEventSender() {
    }

    @Override
    public void send(DomainEvent event) {

    }
}
