package com.dtaquito_backend.dtaquito_backend.reservations.domain.model.commands;

import java.util.Date;

public record CreateReservationsCommand(Date time, Long hours, Long userId, Long sportSpacesId) {

    public CreateReservationsCommand {
        if (time == null) {
            throw new IllegalArgumentException("Time is required");
        }
        if (hours == null) {
            throw new IllegalArgumentException("Hours is required");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID is required");
        }
        if (sportSpacesId == null) {
            throw new IllegalArgumentException("Sport spaces ID is required");
        }
    }
}
