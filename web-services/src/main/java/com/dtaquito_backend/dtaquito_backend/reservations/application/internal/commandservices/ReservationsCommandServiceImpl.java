package com.dtaquito_backend.dtaquito_backend.reservations.application.internal.commandservices;

import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.aggregates.Reservation;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.commands.CreateReservationsCommand;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.services.ReservationsCommandService;
import com.dtaquito_backend.dtaquito_backend.reservations.infrastructure.persistance.jpa.ReservationsRepository;
import com.dtaquito_backend.dtaquito_backend.sportspaces.infrastructure.persistance.jpa.SportSpacesRepository;
import com.dtaquito_backend.dtaquito_backend.users.infrastructure.persistance.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationsCommandServiceImpl implements ReservationsCommandService {

    private final ReservationsRepository reservationsRepository;
    private final SportSpacesRepository sportSpacesRepository;
    private final UserRepository userRepository;

    public ReservationsCommandServiceImpl(ReservationsRepository reservationsRepository, UserRepository userRepository, SportSpacesRepository sportSpacesRepository) {
        this.reservationsRepository = reservationsRepository;
        this.userRepository = userRepository;
        this.sportSpacesRepository = sportSpacesRepository;
    }

    @Override
    public Optional<Reservation> handle(Long userId, CreateReservationsCommand command) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        var sportSpace = sportSpacesRepository.findById(command.sportSpacesId())
                .orElseThrow(() -> new IllegalArgumentException("SportSpace not found"));
        var reservation = new Reservation(command, user, sportSpace);
        var createdReservation = reservationsRepository.save(reservation);
        return Optional.of(createdReservation);
    }

    @Override
    public void handleDelete(Long id) {
        reservationsRepository.deleteById(id);
    }
}
