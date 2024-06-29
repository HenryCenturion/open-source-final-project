package com.dtaquito_backend.dtaquito_backend.reservations.interfaces.rest.resources;

import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.aggregates.SportSpaces;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;

import java.util.Date;

public record ReservationsResource(Long id, Date time, Long hours, User user, SportSpaces sportSpaces) {
}
