package com.dtaquito_backend.dtaquito_backend.payments.application.internal.queryservices;

import java.util.List;
import java.util.Optional;

import com.dtaquito_backend.dtaquito_backend.payments.domain.model.aggregates.Payments;
import com.dtaquito_backend.dtaquito_backend.payments.domain.model.queries.GetAllPaymentsByCardHolderQuery;
import com.dtaquito_backend.dtaquito_backend.payments.domain.model.queries.GetPaymentsByIdQuery;
import com.dtaquito_backend.dtaquito_backend.payments.domain.services.PaymentsQueryService;
import com.dtaquito_backend.dtaquito_backend.payments.infrastructure.persistance.jpa.PaymentsRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentsQueryServiceImpl implements PaymentsQueryService {

    private final PaymentsRepository paymentsRepository;

    public PaymentsQueryServiceImpl(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @Override
    public List<Payments> getAllPayments() {
        return paymentsRepository.findAll();
    }

    @Override
    public List<Payments> handle(GetAllPaymentsByCardHolderQuery query) {
        return paymentsRepository.findAllByCardHolder(query.cardHolder());
    }

    @Override
    public Optional<Payments> getPaymentByUserId(Long userId) {
        return paymentsRepository.findByUserId(userId);
    }

    @Override
    public Optional<Payments> handle(GetPaymentsByIdQuery query) {
        return paymentsRepository.findById(query.id());
    }
}
