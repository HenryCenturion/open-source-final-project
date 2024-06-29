package com.dtaquito_backend.dtaquito_backend.reservations.interfaces.rest;

import com.dtaquito_backend.dtaquito_backend.payments.domain.model.aggregates.Payments;
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
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.services.SportSpacesQueryService;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.aggregates.Suscriptions;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.valueObjects.PlanTypes;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.services.SuscriptionsQueryService;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.valueObjects.RoleTypes;
import com.dtaquito_backend.dtaquito_backend.users.domain.services.UserQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value ="/api/v1/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {
    private final ReservationsQueryService reservationsQueryService;
    private final ReservationsCommandService reservationsCommandService;
    private final UserQueryService userQueryService;
    private final SportSpacesQueryService sportSpacesQueryService;
    private final PaymentsQueryService paymentsQueryService;
    private final PaymentsCommandService paymentsCommandService;
    private final SuscriptionsQueryService suscriptionsQueryService;
    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    public ReservationController(ReservationsQueryService reservationsQueryService, ReservationsCommandService reservationsCommandService, UserQueryService userQueryService, SportSpacesQueryService sportSpacesQueryService, PaymentsQueryService paymentsQueryService, PaymentsCommandService paymentsCommandService
    , SuscriptionsQueryService suscriptionsQueryService) {
        this.reservationsQueryService = reservationsQueryService;
        this.reservationsCommandService = reservationsCommandService;
        this.userQueryService = userQueryService;
        this.sportSpacesQueryService = sportSpacesQueryService;
        this.paymentsQueryService = paymentsQueryService;
        this.paymentsCommandService = paymentsCommandService;
        this.suscriptionsQueryService = suscriptionsQueryService;
    }
    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody CreateReservationsResource resource) {

        Long userId = resource.userId();

        Optional<User> userOptional = userQueryService.getUserById(resource.userId());

        if (!userOptional.isPresent() || !userOptional.get().getRole().getRoleType().equals(RoleTypes.R)) { return new ResponseEntity<>("User is not a RENTOR", HttpStatus.BAD_REQUEST); }

        User user = userOptional.get();

        Optional<Payments> payment = paymentsQueryService.getPaymentByUserId(user.getId());

        if (!payment.isPresent()) { return new ResponseEntity<>("Payment not found", HttpStatus.BAD_REQUEST); }

        double balance = payment.get().getBalance();

        Optional<SportSpaces> sportSpacesOptional = sportSpacesQueryService.handle(new GetSportSpacesByIdQuery(resource.sportSpacesId()));

        if (!sportSpacesOptional.isPresent()) { return new ResponseEntity<>("SportSpaces not found", HttpStatus.BAD_REQUEST); }

        Optional<Suscriptions> subscriptionOptional = suscriptionsQueryService.getSubscriptionByUserId(userId);

        if (!subscriptionOptional.isPresent()) { return new ResponseEntity<>("Subscription not found", HttpStatus.BAD_REQUEST); }

        PlanTypes subscriptionPlan = subscriptionOptional.get().getPlan().getPlanType();

        SportSpaces sportSpaces = sportSpacesOptional.get();

        LocalDateTime reservationDateTime;
        try { reservationDateTime = LocalDateTime.ofInstant(resource.time().toInstant(), ZoneId.systemDefault()); }
        catch (DateTimeParseException e) { return new ResponseEntity<>("Invalid time format. Please use the format 'HH:mm'", HttpStatus.BAD_REQUEST); }

        LocalDateTime reservationDateTimeUTC = resource.time().toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
        LocalDateTime sportSpacesStartTimeUTC = LocalDateTime.of(reservationDateTimeUTC.toLocalDate(), LocalTime.parse(sportSpaces.getStartTime(), DateTimeFormatter.ISO_LOCAL_TIME));
        LocalDateTime sportSpacesEndTimeUTC = LocalDateTime.of(reservationDateTimeUTC.toLocalDate(), LocalTime.parse(sportSpaces.getEndTime(), DateTimeFormatter.ISO_LOCAL_TIME));

        logger.info("Reservation time: {}", reservationDateTime);

        if (reservationDateTimeUTC.isBefore(sportSpacesStartTimeUTC) || reservationDateTimeUTC.isAfter(sportSpacesEndTimeUTC)) { return new ResponseEntity<>("Reservation time is not within the allowed range", HttpStatus.BAD_REQUEST); }

        Date reservationDate = Date.from(reservationDateTime.atZone(ZoneId.systemDefault()).toInstant());

        List<Reservation> existingReservations = reservationsQueryService.getReservationsBySportSpacesId(resource.sportSpacesId());

        boolean isAlreadyReserved = existingReservations.stream().anyMatch(reservation -> {
                    LocalDateTime existingReservationTime = reservation.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    return existingReservationTime.equals(reservationDateTime) && reservation.getUser().getId().equals(resource.userId());
                });

        if (isAlreadyReserved) { return new ResponseEntity<>("Reservation time is already reserved", HttpStatus.BAD_REQUEST); }

        boolean isAlreadyReservedByAnotherUser = existingReservations.stream().anyMatch(reservation -> reservation.getTime().equals(resource.time()) && !reservation.getUser().getId().equals(resource.userId()));

        if (isAlreadyReservedByAnotherUser) { return new ResponseEntity<>("Reservation time is already reserved by another user", HttpStatus.BAD_REQUEST); }

        CreateReservationsResource newresource = new CreateReservationsResource(reservationDate, resource.hours(), resource.userId(), resource.sportSpacesId());

        LocalDateTime reservationEndTime = reservationDateTime.plusHours(resource.hours());

        if (reservationEndTime.isAfter(sportSpacesEndTimeUTC)) { return new ResponseEntity<>("Reservation duration is not within the allowed range", HttpStatus.BAD_REQUEST); }

        Optional<Reservation> reservationOptional = reservationsCommandService.handle(userId, CreateReservationsCommandFromResourceAssembler.toCommandFromResource(newresource));

        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();

            if (subscriptionPlan.equals(PlanTypes.free)) { balance -= sportSpacesOptional.get().getPrice(); }
            else if (subscriptionPlan.equals(PlanTypes.premium)) { balance -= (double) sportSpacesOptional.get().getPrice() / 2; }

            payment.get().setBalance((long) balance);
            paymentsCommandService.updatePayments(payment.get());

            logger.info("Reservation created successfully for user {}", resource.userId());
            return new ResponseEntity<>(ReservationsResourceFromEntityAssembler.toResourceFromEntity(reservation), HttpStatus.CREATED);
        } else {
            logger.warn("Reservation could not be created for user {}", resource.userId());
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