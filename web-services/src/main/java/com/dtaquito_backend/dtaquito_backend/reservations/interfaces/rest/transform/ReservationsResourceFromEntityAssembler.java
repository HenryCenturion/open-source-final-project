package com.dtaquito_backend.dtaquito_backend.reservations.interfaces.rest.transform;

import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.aggregates.Reservation;
import com.dtaquito_backend.dtaquito_backend.reservations.interfaces.rest.resources.ReservationsResource;

public class ReservationsResourceFromEntityAssembler {

    public static ReservationsResource toResourceFromEntity(Reservation entity) {
        return new ReservationsResource(entity.getId(), entity.getTime(), entity.getHours(), entity.getUser(), entity.getSportSpaces());
    }
}
