package com.dtaquito_backend.dtaquito_backend.payments.domain.model.queries;

public record GetAllPaymentsByCardHolderQuery(String cardHolder) {

    public GetAllPaymentsByCardHolderQuery {

        if (cardHolder == null || cardHolder.isBlank()) {
            throw new IllegalArgumentException("Card holder is required");
        }
    }

}
