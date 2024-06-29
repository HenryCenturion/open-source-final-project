package com.dtaquito_backend.dtaquito_backend.reservations.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ReservationHours(Long reservationHours) {

    public ReservationHours() {this(0L);}

    public ReservationHours {
        if (reservationHours == null) {
            throw new IllegalArgumentException("Reservation hours cannot be null");
        }
        if (reservationHours < 0) {
            throw new IllegalArgumentException("Reservation hours cannot be negative");
        }
        if (reservationHours > 0 && reservationHours < 3) {
            throw new IllegalArgumentException("Reservation hours must be at least 1 hour long and at most 2 hours");
        }
    }
}
