package com.dtaquito_backend.dtaquito_backend.payments.application.internal.commandservices;

import com.dtaquito_backend.dtaquito_backend.payments.domain.model.aggregates.Payments;
import com.dtaquito_backend.dtaquito_backend.payments.domain.model.commands.CreatePaymentsCommand;
import com.dtaquito_backend.dtaquito_backend.payments.domain.services.PaymentsCommandService;
import com.dtaquito_backend.dtaquito_backend.payments.infrastructure.persistance.jpa.PaymentsRepository;
import com.dtaquito_backend.dtaquito_backend.users.infrastructure.persistance.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentsCommandServiceImpl implements PaymentsCommandService {

    private final PaymentsRepository paymentsRepository;

    private final UserRepository userRepository;

    public PaymentsCommandServiceImpl(PaymentsRepository paymentsRepository, UserRepository userRepository) {
        this.paymentsRepository = paymentsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Payments> handle(Long userId, CreatePaymentsCommand command) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        var payments = new Payments(command, user);
        var createdPayments = paymentsRepository.save(payments);
        return Optional.of(createdPayments);
    }

    @Override
    public void handleDelete(Long id) {
        paymentsRepository.deleteById(id);
    }

    @Override
    public void updatePayments(Payments payment) {
        paymentsRepository.save(payment);
    }
}
