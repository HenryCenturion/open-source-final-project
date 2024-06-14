package com.dtaquito_backend.dtaquito_backend.reservations.domain.services;

import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.aggregates.Reservation;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.commands.CreateReservationsCommand;
import com.dtaquito_backend.dtaquito_backend.reservations.interfaces.rest.resources.ReservationsResource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ReservationsCommandService {

    Optional<Reservation> handle(Long id, CreateReservationsCommand command);
    void handleDelete(Long id);
}
