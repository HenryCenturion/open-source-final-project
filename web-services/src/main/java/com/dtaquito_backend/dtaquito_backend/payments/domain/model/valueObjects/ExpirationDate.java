package com.dtaquito_backend.dtaquito_backend.payments.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

import java.util.Date;

@Embeddable
public record ExpirationDate(Date expirationDate) {

    public ExpirationDate() {this(null);}

    public ExpirationDate {
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null");
        }
        if (expirationDate.before(new Date())) {
            throw new IllegalArgumentException("Expiration date must be in the future");
        }
    }
}
