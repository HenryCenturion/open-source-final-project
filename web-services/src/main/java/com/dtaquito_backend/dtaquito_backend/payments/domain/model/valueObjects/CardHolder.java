package com.dtaquito_backend.dtaquito_backend.payments.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CardHolder(String cardHolder) {

    public CardHolder() {this(null);}

    public CardHolder {
        if (cardHolder == null) {
            throw new IllegalArgumentException("Card holder cannot be null");
        }
        if (cardHolder.isBlank()) {
            throw new IllegalArgumentException("Card holder cannot be blank");
        }
        if (cardHolder.length() < 3) {
            throw new IllegalArgumentException("Card holder must be at least 3 characters long");
        }
        if (cardHolder.length() > 15) {
            throw new IllegalArgumentException("Card holder must be at most 15 characters long");
        }
    }
}
