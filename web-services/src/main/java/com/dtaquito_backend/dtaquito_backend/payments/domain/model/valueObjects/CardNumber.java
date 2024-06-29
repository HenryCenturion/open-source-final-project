package com.dtaquito_backend.dtaquito_backend.payments.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CardNumber(String cardNumber) {

    public CardNumber() {this(null);}

    public CardNumber {
        if (cardNumber == null) {
            throw new IllegalArgumentException("Card number cannot be null");
        }
        if (cardNumber.isBlank()) {
            throw new IllegalArgumentException("Card number cannot be blank");
        }
        if (cardNumber.length() < 17) {
            throw new IllegalArgumentException("Card number must be at least 16 characters long");
        }
    }
}
