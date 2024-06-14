package com.dtaquito_backend.dtaquito_backend.sportspaces.interfaces.rest;


import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.aggregates.SportSpaces;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.queries.GetAllSportSpacesByNameQuery;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.queries.GetSportSpacesByIdQuery;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.queries.GetSportSpacesByUserId;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.services.SportSpacesCommandService;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.services.SportSpacesQueryService;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.aggregates.Suscriptions;
import com.dtaquito_backend.dtaquito_backend.suscriptions.interfaces.rest.transform.SuscriptionsResourceFromEntityAssembler;
import com.dtaquito_backend.dtaquito_backend.users.domain.services.UserQueryService;
import com.dtaquito_backend.dtaquito_backend.sportspaces.interfaces.rest.resources.CreateSportSpacesResource;
import com.dtaquito_backend.dtaquito_backend.sportspaces.interfaces.rest.resources.SportSpacesResource;
import com.dtaquito_backend.dtaquito_backend.sportspaces.interfaces.rest.transform.CreateSportSpacesCommandFromResourceAssembler;
import com.dtaquito_backend.dtaquito_backend.sportspaces.interfaces.rest.transform.SportSpacesResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/sport-spaces")
public class SportSpacesController {

    private final SportSpacesCommandService sportSpacesCommandService;

    private final SportSpacesQueryService sportSpacesQueryService;
    private final UserQueryService userQueryService;

    public SportSpacesController(SportSpacesCommandService sportSpacesCommandService, SportSpacesQueryService sportSpacesQueryService, UserQueryService userQueryService) {
        this.sportSpacesCommandService = sportSpacesCommandService;
        this.sportSpacesQueryService = sportSpacesQueryService;
        this.userQueryService = userQueryService;
    }

    @PostMapping
    public ResponseEntity<SportSpacesResource> createSportSpaces(@RequestBody CreateSportSpacesResource resource) {
        Long userId = resource.userId();
        String userRole = userQueryService.getUserRoleByUserId(userId);
        String userSubscriptionPlan = userQueryService.getUserSubscriptionPlan(userId);
        if (userRole == null || !userRole.equals("P") || userSubscriptionPlan == null || !userSubscriptionPlan.equals("premium")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
        String userRole = userQueryService.getUserRoleByUserId(resource.userId());
        if (userRole == null || !userRole.equals("P")) {
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
        Optional<SportSpaces> existingSportSpaces = sportSpacesQueryService.handle(new GetSportSpacesByIdQuery(id));
        if (!existingSportSpaces.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Long userId = existingSportSpaces.get().getUser().getId();
        String userRole = userQueryService.getUserRoleByUserId(userId);
        if (userRole == null || !userRole.equals("P")) {
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
