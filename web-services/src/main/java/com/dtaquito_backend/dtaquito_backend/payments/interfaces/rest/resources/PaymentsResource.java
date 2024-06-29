package com.dtaquito_backend.dtaquito_backend.payments.interfaces.rest.resources;

import java.util.Date;

import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;

public record PaymentsResource(Long id, String cardNumber, Date expirationDate, String cardHolder, String cardIssuer, String cvv, User user, Long balance) {

}
