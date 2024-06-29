package com.dtaquito_backend.dtaquito_backend.payments.interfaces.rest.resources;

import java.util.Date;

public record CreatePaymentsResource(String cardNumber, Date expirationDate, String cardHolder, String cardIssuer, String cvv, Long userId, Long balance) {

    public CreatePaymentsResource {
        if (cardNumber == null) {
            throw new IllegalArgumentException("Card number cannot be null");
        }
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null");
        }
        if (cardHolder == null) {
            throw new IllegalArgumentException("Card holder cannot be null");
        }
        if (cardIssuer == null) {
            throw new IllegalArgumentException("Card issuer cannot be null");
        }
        if (cvv == null) {
            throw new IllegalArgumentException("CVV cannot be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (balance == null) {
            throw new IllegalArgumentException("Balance cannot be null");
        }
    }

}
