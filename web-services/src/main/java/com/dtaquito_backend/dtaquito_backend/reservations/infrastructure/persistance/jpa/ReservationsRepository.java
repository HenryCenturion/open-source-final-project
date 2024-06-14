package com.dtaquito_backend.dtaquito_backend.reservations.infrastructure.persistance.jpa;

import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.aggregates.Reservation;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.aggregates.SportSpaces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationsRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUserId(Long userId);
}
