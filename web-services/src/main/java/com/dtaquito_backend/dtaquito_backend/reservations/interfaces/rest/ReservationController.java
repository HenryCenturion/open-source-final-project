package com.dtaquito_backend.dtaquito_backend.reservations.interfaces.rest;

import com.dtaquito_backend.dtaquito_backend.payments.domain.services.PaymentsCommandService;
import com.dtaquito_backend.dtaquito_backend.payments.domain.services.PaymentsQueryService;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.aggregates.Reservation;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.queries.GetReservationsByIdQuery;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.queries.GetReservationsByUserId;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.services.ReservationsCommandService;
import com.dtaquito_backend.dtaquito_backend.reservations.domain.services.ReservationsQueryService;
import com.dtaquito_backend.dtaquito_backend.reservations.interfaces.rest.resources.CreateReservationsResource;
import com.dtaquito_backend.dtaquito_backend.reservations.interfaces.rest.resources.ReservationsResource;
import com.dtaquito_backend.dtaquito_backend.reservations.interfaces.rest.transform.CreateReservationsCommandFromResourceAssembler;
import com.dtaquito_backend.dtaquito_backend.reservations.interfaces.rest.transform.ReservationsResourceFromEntityAssembler;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.aggregates.SportSpaces;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.queries.GetSportSpacesByIdQuery;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.queries.GetSportSpacesByUserId;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.services.SportSpacesQueryService;
import com.dtaquito_backend.dtaquito_backend.sportspaces.interfaces.rest.resources.SportSpacesResource;
import com.dtaquito_backend.dtaquito_backend.sportspaces.interfaces.rest.transform.SportSpacesResourceFromEntityAssembler;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.dtaquito_backend.dtaquito_backend.users.domain.services.UserQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
    private final ReservationsQueryService reservationsQueryService;
    private final ReservationsCommandService reservationsCommandService;
    private final UserQueryService userQueryService;
    private final SportSpacesQueryService sportSpacesQueryService;
    private final PaymentsQueryService paymentsQueryService;
    private final PaymentsCommandService paymentsCommandService;

    public ReservationController(ReservationsQueryService reservationsQueryService, ReservationsCommandService reservationsCommandService, UserQueryService userQueryService, SportSpacesQueryService sportSpacesQueryService, PaymentsQueryService paymentsQueryService, PaymentsCommandService paymentsCommandService) {
        this.reservationsQueryService = reservationsQueryService;
        this.reservationsCommandService = reservationsCommandService;
        this.userQueryService = userQueryService;
        this.sportSpacesQueryService = sportSpacesQueryService;
        this.paymentsQueryService = paymentsQueryService;
        this.paymentsCommandService = paymentsCommandService;
    }
    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody CreateReservationsResource resource) {

        Long userId = resource.userId();

        // Obtén el usuario a partir del ID proporcionado en la solicitud
        Optional<User> userOptional = userQueryService.getUserById(resource.userId());

        // Verifica si el usuario es un "RENTOR"
        if (!userOptional.isPresent() || !userOptional.get().getRole().equals("R")) {
            return new ResponseEntity<>("User is not a RENTOR", HttpStatus.BAD_REQUEST);
        }

        User user = userOptional.get();

        // Obtén el balance del usuario
        var payment = paymentsQueryService.getPaymentByUserId(user.getId());
        var balance = payment.get().getBalance();

        Optional<SportSpaces> sportSpacesOptional = sportSpacesQueryService.handle(new GetSportSpacesByIdQuery(resource.sportSpacesId()));

        if (!sportSpacesOptional.isPresent()) {
            return new ResponseEntity<>("SportSpaces not found", HttpStatus.BAD_REQUEST);
        }

        String subscriptionPlan = userQueryService.getUserSubscriptionPlan(user.getId());

        if (subscriptionPlan.equals("free")) {
            balance -= sportSpacesOptional.get().getPrice();
        } else if (subscriptionPlan.equals("premium")) {
            balance -= sportSpacesOptional.get().getPrice() / 2;
        }
        if (balance < 0) {
            return new ResponseEntity<>("User does not have enough balance", HttpStatus.BAD_REQUEST);
        }

        if (payment.isPresent()) {
            payment.get().setBalance(balance);
            paymentsCommandService.updatePayments(payment.get());
        }

        CreateReservationsResource newresource = new CreateReservationsResource(
                resource.time(),
                resource.hours(),
                resource.userId(),
                resource.sportSpacesId()
        );
        Optional<Reservation> reservationOptional = reservationsCommandService.handle(userId, CreateReservationsCommandFromResourceAssembler.toCommandFromResource(newresource));

        if (reservationOptional.isPresent()) {
            return new ResponseEntity<>(ReservationsResourceFromEntityAssembler.toResourceFromEntity(reservationOptional.get()), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Reservation could not be created", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationsResource> getUserById(@PathVariable Long id) {
        Optional<Reservation> reservations = reservationsQueryService.handle(new GetReservationsByIdQuery(id));
        return reservations.map(source -> ResponseEntity.ok(ReservationsResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(params = {"!time", "!price", "!hours", "!userId", "!sport_spaces_id"})
    public ResponseEntity<List<ReservationsResource>> getAllReservations() {
        var reservations = reservationsQueryService.getAllReservations();
        var reservationsResource = reservations.stream().map(ReservationsResourceFromEntityAssembler::toResourceFromEntity);
        return ResponseEntity.ok(reservationsResource.toList());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationsQueryService.handle(new GetReservationsByIdQuery(id));

        if (reservation.isEmpty()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
        reservationsCommandService.handleDelete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(params = {"userId"})
    public ResponseEntity<List<ReservationsResource>> getReservationsByUserId(@RequestParam("userId") Long userId) {
        List<Reservation> reservations = reservationsQueryService.handle(new GetReservationsByUserId(userId));
        if (reservations.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<ReservationsResource> reservationsResources = reservations.stream()
                    .map(ReservationsResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(reservationsResources);
        }
    }

}