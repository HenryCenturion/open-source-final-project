package com.dtaquito_backend.dtaquito_backend.payments.domain.services;

import java.util.List;
import java.util.Optional;

import com.dtaquito_backend.dtaquito_backend.payments.domain.model.aggregates.Payments;
import com.dtaquito_backend.dtaquito_backend.payments.domain.model.queries.GetAllPaymentsByCardHolderQuery;
import com.dtaquito_backend.dtaquito_backend.payments.domain.model.queries.GetPaymentsByIdQuery;


public interface PaymentsQueryService {

    List<Payments> handle(GetAllPaymentsByCardHolderQuery query);
    Optional<Payments> handle(GetPaymentsByIdQuery query);
    Optional<Payments> getPaymentByUserId(Long userId);
    List<Payments> getAllPayments();
}
