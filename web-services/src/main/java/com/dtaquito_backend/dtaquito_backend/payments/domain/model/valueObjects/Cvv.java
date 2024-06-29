package com.dtaquito_backend.dtaquito_backend.payments.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Cvv(String cvv) {

    public Cvv() {this(null);}

    public Cvv {
        if (cvv == null) {
            throw new IllegalArgumentException("CVV cannot be null");
        }
        if (cvv.isBlank()) {
            throw new IllegalArgumentException("CVV cannot be blank");
        }
        if (cvv.length() < 3) {
            throw new IllegalArgumentException("CVV must be at least 3 characters long");
        }
    }
}
