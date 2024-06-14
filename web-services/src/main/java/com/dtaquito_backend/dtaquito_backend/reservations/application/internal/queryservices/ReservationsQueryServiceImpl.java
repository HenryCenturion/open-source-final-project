package com.dtaquito_backend.dtaquito_backend.reservations.application.internal.queryservices;

import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.aggregates.Reservation;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.queries.GetReservationsByIdQuery;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.queries.GetReservationsByUserId;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.services.ReservationsQueryService;
import com.dtaquito_backend.dtaquito_backend.reservations.infrastructure.persistance.jpa.ReservationsRepository;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.aggregates.SportSpaces;
import com.dtaquito_backend.dtaquito_backend.sportspaces.infrastructure.persistance.jpa.SportSpacesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationsQueryServiceImpl implements ReservationsQueryService {

    private final ReservationsRepository reservationsRepository;
    private final SportSpacesRepository sportSpacesRepository;

    public ReservationsQueryServiceImpl(ReservationsRepository reservationsRepository, SportSpacesRepository sportSpacesRepository) {
        this.reservationsRepository = reservationsRepository;
        this.sportSpacesRepository = sportSpacesRepository;
    }

    @Override
    public Optional<Reservation> handle(GetReservationsByIdQuery query) {
        return reservationsRepository.findById(query.id());
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationsRepository.findAll();
    }

    @Override
    public List<Reservation> handle(GetReservationsByUserId query) {
        return reservationsRepository.findByUserId(query.userId());
    }
}
