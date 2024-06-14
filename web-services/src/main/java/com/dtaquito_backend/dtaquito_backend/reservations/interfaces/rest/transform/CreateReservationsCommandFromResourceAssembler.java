package com.dtaquito_backend.dtaquito_backend.reservations.interfaces.rest.transform;

import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.commands.CreateReservationsCommand;
import com.dtaquito_backend.dtaquito_backend.reservations.interfaces.rest.resources.CreateReservationsResource;

public class CreateReservationsCommandFromResourceAssembler {

    public static CreateReservationsCommand toCommandFromResource(CreateReservationsResource resource) {
        return new CreateReservationsCommand(resource.time(), resource.hours(), resource.userId(), resource.sportSpacesId());
    }
}
