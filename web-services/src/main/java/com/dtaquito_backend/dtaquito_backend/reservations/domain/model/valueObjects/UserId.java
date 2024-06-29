package com.dtaquito_backend.dtaquito_backend.reservations.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserId(Long userId) {

    public UserId() {this(0L);}

    public UserId {
        if (userId == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }
        if (userId < 0) {
            throw new IllegalArgumentException("User id cannot be negative");
        }
    }
}
