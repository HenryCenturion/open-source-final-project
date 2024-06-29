package com.dtaquito_backend.dtaquito_backend.payments.infrastructure.persistance.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtaquito_backend.dtaquito_backend.payments.domain.model.aggregates.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {

    List<Payments> findAllByCardHolder(String cardHolder);
    Optional<Payments> findByUserId(Long userId);
}
