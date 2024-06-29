package com.dtaquito_backend.dtaquito_backend.sportspaces.interfaces.rest;


import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.aggregates.SportSpaces;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.queries.GetAllSportSpacesByNameQuery;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.queries.GetSportSpacesByIdQuery;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.queries.GetSportSpacesByUserId;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.services.SportSpacesCommandService;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.services.SportSpacesQueryService;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.aggregates.Suscriptions;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.valueObjects.PlanTypes;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.services.SuscriptionsQueryService;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.valueObjects.RoleTypes;
import com.dtaquito_backend.dtaquito_backend.users.domain.services.UserQueryService;
import com.dtaquito_backend.dtaquito_backend.sportspaces.interfaces.rest.resources.CreateSportSpacesResource;
import com.dtaquito_backend.dtaquito_backend.sportspaces.interfaces.rest.resources.SportSpacesResource;
import com.dtaquito_backend.dtaquito_backend.sportspaces.interfaces.rest.transform.CreateSportSpacesCommandFromResourceAssembler;
import com.dtaquito_backend.dtaquito_backend.sportspaces.interfaces.rest.transform.SportSpacesResourceFromEntityAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/sport-spaces", produces = MediaType.APPLICATION_JSON_VALUE)
public class SportSpacesController {

    private final SportSpacesCommandService sportSpacesCommandService;

    private final SportSpacesQueryService sportSpacesQueryService;
    private final UserQueryService userQueryService;
    private final SuscriptionsQueryService suscriptionsQueryService;

    public SportSpacesController(SportSpacesCommandService sportSpacesCommandService, SportSpacesQueryService sportSpacesQueryService, UserQueryService userQueryService, SuscriptionsQueryService suscriptionsQueryService) {
        this.sportSpacesCommandService = sportSpacesCommandService;
        this.sportSpacesQueryService = sportSpacesQueryService;
        this.suscriptionsQueryService = suscriptionsQueryService;
        this.userQueryService = userQueryService;
    }

    private static final Logger logger = LoggerFactory.getLogger(SportSpacesController.class);

    @PostMapping
    public ResponseEntity<SportSpacesResource> createSportSpaces(@RequestBody CreateSportSpacesResource resource) {
        Long userId = resource.userId();
        logger.info("Creating sport spaces for user with ID: {}", userId);
        RoleTypes userRole;
        PlanTypes userSubscriptionPlan;
        try {
            userRole = RoleTypes.valueOf(userQueryService.getUserRoleByUserId(userId));
            Suscriptions suscription = suscriptionsQueryService.getSubscriptionByUserId(userId).orElseThrow(() -> new IllegalArgumentException("Subscription not found for user"));
            userSubscriptionPlan = suscription.getPlan().getPlanType(); // Get the PlanTypes value from the Plan object
        } catch (IllegalArgumentException e) {
            logger.error("Invalid user role or subscription plan", e);
            throw new IllegalArgumentException("Invalid user role or subscription plan", e);
        }
        if (!userRole.equals(RoleTypes.P) || !userSubscriptionPlan.equals(PlanTypes.premium)) {
            logger.error("User role must be 'P' and subscription plan must be 'premium'");
            throw new IllegalArgumentException("User role must be 'P' and subscription plan must be 'premium'");
        }

        Optional<SportSpaces> sportSpaces = sportSpacesCommandService.handle(userId, CreateSportSpacesCommandFromResourceAssembler.toCommandFromResource((resource)));
        return sportSpaces.map(source -> new ResponseEntity<>(SportSpacesResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<SportSpacesResource> getUserById(@PathVariable Long id) {
        Optional<SportSpaces> sportSpaces = sportSpacesQueryService.handle(new GetSportSpacesByIdQuery(id));
        return sportSpaces.map(source -> ResponseEntity.ok(SportSpacesResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<List<SportSpacesResource>> getAllUsersByName(String name){

        var getAllSportSpacesByNameQuery = new GetAllSportSpacesByNameQuery(name);
        var sportSpaces = sportSpacesQueryService.handle(getAllSportSpacesByNameQuery);
        if (sportSpaces.isEmpty()) return ResponseEntity.notFound().build();
        var sportSpacesResources = sportSpaces.stream().map(SportSpacesResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(sportSpacesResources);
    }

    @GetMapping(params = {"!name", "!id", "!imageUrl", "!description", "!price", "!userId", "!startTime", "!endTime", "!status", "!hours"})
    public ResponseEntity<List<SportSpacesResource>> getAllSportSpaces(){
        var sportSpaces = sportSpacesQueryService.getAllSportSpaces();
        var sportSpacesResources = sportSpaces.stream().map(SportSpacesResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(sportSpacesResources);
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<?> getUsersWithParameters(@RequestParam Map<String, String> params) {

        if (params.containsKey("name")) {
            return getAllUsersByName(params.get("name"));
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<SportSpacesResource> updateSportSpaces(@PathVariable Long id, @RequestBody CreateSportSpacesResource resource) {
        RoleTypes userRole;
        try {
            userRole = RoleTypes.valueOf(userQueryService.getUserRoleByUserId(resource.userId()));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (!userRole.equals(RoleTypes.P)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Optional<SportSpaces> existingSportSpaces = sportSpacesQueryService.handle(new GetSportSpacesByIdQuery(id));
        if (!existingSportSpaces.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<SportSpaces> sportSpaces = sportSpacesCommandService.handleUpdate(id, CreateSportSpacesCommandFromResourceAssembler.toCommandFromResource((resource)));
        return sportSpaces.map(source -> new ResponseEntity<>(SportSpacesResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSportSpaces(@PathVariable Long id) {
        // Get the existing sport space
        Optional<SportSpaces> existingSportSpaces = sportSpacesQueryService.handle(new GetSportSpacesByIdQuery(id));

        // Check if the sport space exists
        if (!existingSportSpaces.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Get the user ID
        Long userId = existingSportSpaces.get().getUser().getId();

        // Get the user rol
        RoleTypes userRole = RoleTypes.valueOf(userQueryService.getUserRoleByUserId(userId));;

        if (!userRole.equals(RoleTypes.P)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        sportSpacesCommandService.handleDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(params = {"userId"})
    public ResponseEntity<List<SportSpacesResource>> getSportSpacesByUserId(@RequestParam("userId") Long userId) {
        List<SportSpaces> sportSpaces = sportSpacesQueryService.handle(new GetSportSpacesByUserId(userId));
        if (sportSpaces.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<SportSpacesResource> sportSpacesResources = sportSpaces.stream()
                    .map(SportSpacesResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(sportSpacesResources);
        }
    }
}