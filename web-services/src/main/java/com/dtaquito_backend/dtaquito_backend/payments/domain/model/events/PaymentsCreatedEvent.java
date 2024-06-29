package com.dtaquito_backend.dtaquito_backend.payments.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PaymentsCreatedEvent extends ApplicationEvent {

    private final Long id;

    public PaymentsCreatedEvent(Object source, Long id) {
        super(source);
        this.id = id;
    }
}
