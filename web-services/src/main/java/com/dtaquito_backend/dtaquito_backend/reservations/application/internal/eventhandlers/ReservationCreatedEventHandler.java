package com.dtaquito_backend.dtaquito_backend.reservations.application.internal.eventhandlers;

import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.events.ReservationCreatedEvent;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.queries.GetReservationsByUserId;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.services.ReservationsCommandService;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.services.ReservationsQueryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ReservationCreatedEventHandler {

    private final ReservationsQueryService reservationQueryService;
    private final ReservationsCommandService reservationCommandService;

    public ReservationCreatedEventHandler(ReservationsQueryService reservationQueryService,
                                          @Qualifier("reservationsCommandServiceImpl") ReservationsCommandService reservationCommandService) {
        this.reservationQueryService = reservationQueryService;
        this.reservationCommandService = reservationCommandService;
    }

    @EventListener(ReservationCreatedEvent.class)
    public void on(ReservationCreatedEvent event){
        System.out.println("ReservationCreatedEvent received for reservation ID: " + event.getReservationId());

        reservationCommandService.handleReservationCreatedEvent(event);

        var getReservationByIdQuery = new GetReservationsByUserId(event.getReservationId());

        var reservation = reservationQueryService.handle(getReservationByIdQuery);

        if (!reservation.isEmpty()){
            System.out.println("Reservation with ID " + event.getReservationId() + " has been created.");
        } else {
            System.out.println("No reservation found with ID " + event.getReservationId());
        }
    }
}
