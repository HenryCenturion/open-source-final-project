package com.dtaquito_backend.dtaquito_backend.reservations.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record SuscriptionId(Long suscriptionId) {

    public SuscriptionId() {this(0L);}

    public SuscriptionId {
        if (suscriptionId == null) {
            throw new IllegalArgumentException("Suscription id cannot be null");
        }
        if (suscriptionId < 0) {
            throw new IllegalArgumentException("Suscription id cannot be negative");
        }
    }
}
