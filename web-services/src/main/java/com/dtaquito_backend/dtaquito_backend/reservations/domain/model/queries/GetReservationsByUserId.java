package com.dtaquito_backend.dtaquito_backend.reservations.domain.model.queries;

public record GetReservationsByUserId(Long userId) {
    public GetReservationsByUserId {
        if (userId == null) {
            throw new IllegalArgumentException("UserId is required");
        }
    }
}
