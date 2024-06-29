package com.dtaquito_backend.dtaquito_backend.reservations.domain.model.valueObjects;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public record ReservationTime(LocalDateTime reservationTime) {

    public ReservationTime() {this(LocalDateTime.now());}

    public ReservationTime {
        if (reservationTime == null) {
            throw new IllegalArgumentException("Reservation time cannot be null");
        }
        if (reservationTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Reservation time cannot be in the past");
        }
    }
}
