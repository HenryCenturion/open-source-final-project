package com.dtaquito_backend.dtaquito_backend.reservations.domain.services;

import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.aggregates.Reservation;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.queries.GetReservationsByIdQuery;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.queries.GetReservationsByUserId;

import java.util.List;
import java.util.Optional;

public interface ReservationsQueryService {

    Optional<Reservation> handle(GetReservationsByIdQuery query);

    List<Reservation> getAllReservations();

    List<Reservation> handle(GetReservationsByUserId query);

    List<Reservation> getReservationsBySportSpacesId(Long sportSpacesId);
}
