package com.dtaquito_backend.dtaquito_backend.reservations.interfaces.rest.resources;

import java.util.Date;

public record CreateReservationsResource(Date time, Long hours, Long userId, Long sportSpacesId) {

    public CreateReservationsResource {
        if (time == null) {
            throw new IllegalArgumentException("Time cannot be null");
        }
        if (hours == null) {
            throw new IllegalArgumentException("Hours cannot be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }
        if (sportSpacesId == null) {
            throw new IllegalArgumentException("SportSpaceId cannot be null");
        }
    }
}
