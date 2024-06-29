package com.dtaquito_backend.dtaquito_backend.reservations.domain.model.queries;

public record GetReservationsByIdQuery(Long id) {

    public GetReservationsByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Id is required");
        }
    }
}
