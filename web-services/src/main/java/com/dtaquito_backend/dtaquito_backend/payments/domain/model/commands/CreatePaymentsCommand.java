package com.dtaquito_backend.dtaquito_backend.payments.domain.model.commands;

import java.util.Date;

public record CreatePaymentsCommand(String cardNumber, Date expirationDate, String cardHolder, String cardIssuer, String cvv, Long userId, Long balance) {

    public CreatePaymentsCommand {

        if (cardNumber == null || cardNumber.isBlank()) {
            throw new IllegalArgumentException("Card number is required");
        }
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date is required");
        }
        if (cardHolder == null || cardHolder.isBlank()) {
            throw new IllegalArgumentException("Card holder is required");
        }
        if (cardIssuer == null || cardIssuer.isBlank()) {
            throw new IllegalArgumentException("Card issuer is required");
        }
        if (cvv == null || cvv.isBlank()) {
            throw new IllegalArgumentException("CVV is required");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID is required");
        }
        if (balance == null) {
            throw new IllegalArgumentException("Balance is required");
        }
    }

}
