package com.dtaquito_backend.dtaquito_backend.payments.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CardIssuer(String cardIssuer) {

    public CardIssuer() {this(null);}

    public CardIssuer {
        if (cardIssuer == null) {
            throw new IllegalArgumentException("Card issuer cannot be null");
        }
        if (cardIssuer.isBlank()) {
            throw new IllegalArgumentException("Card issuer cannot be blank");
        }
    }
}
