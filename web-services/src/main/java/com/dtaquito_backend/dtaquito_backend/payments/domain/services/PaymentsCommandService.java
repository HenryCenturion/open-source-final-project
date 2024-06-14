package com.dtaquito_backend.dtaquito_backend.payments.domain.services;

import java.util.Optional;

import com.dtaquito_backend.dtaquito_backend.payments.domain.model.aggregates.Payments;
import com.dtaquito_backend.dtaquito_backend.payments.domain.model.commands.CreatePaymentsCommand;


public interface PaymentsCommandService {

    Optional<Payments> handle(Long id, CreatePaymentsCommand command    );
    void handleDelete(Long id);
    void updatePayments(Payments payment);
}
