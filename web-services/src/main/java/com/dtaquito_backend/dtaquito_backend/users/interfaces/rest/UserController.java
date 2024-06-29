package com.dtaquito_backend.dtaquito_backend.users.interfaces.rest;

import java.util.*;
import static org.springframework.http.HttpStatus.CREATED;

import com.dtaquito_backend.dtaquito_backend.iam.application.internal.outboundservices.tokens.TokenService;
import com.dtaquito_backend.dtaquito_backend.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.dtaquito_backend.dtaquito_backend.iam.interfaces.rest.resources.SignUpResource;
import com.dtaquito_backend.dtaquito_backend.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.entities.Plan;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.valueObjects.PlanTypes;
import com.dtaquito_backend.dtaquito_backend.suscriptions.infrastructure.persistance.jpa.PlanRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.antlr.v4.runtime.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.services.SuscriptionsCommandService;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.commands.CreateSuscriptionsCommand;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.queries.GetAllUserByNameQuery;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.queries.GetUserByIdQuery;
import com.dtaquito_backend.dtaquito_backend.users.domain.services.UserCommandService;
import com.dtaquito_backend.dtaquito_backend.users.domain.services.UserQueryService;
import com.dtaquito_backend.dtaquito_backend.users.interfaces.rest.resources.UserResource;
import com.dtaquito_backend.dtaquito_backend.users.interfaces.rest.transform.UserResourceFromEntityAssembler;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Users Controller")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final SuscriptionsCommandService suscriptionsCommandService;
    private final PlanRepository planRepository;
    private final TokenService tokenService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService, SuscriptionsCommandService suscriptionsCommandService, PlanRepository planRepository, TokenService tokenService) { // Modify this line
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
        this.suscriptionsCommandService = suscriptionsCommandService;
        this.planRepository = planRepository;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<AuthenticatedUserResource> createUser(@RequestBody SignUpResource resource){
        try {
            Optional<User> userPair = userCommandService.handle(SignUpCommandFromResourceAssembler.toCommandFromResource((resource)));
            if (userPair.isPresent()) {
                Plan freePlan = planRepository.findByPlanType(PlanTypes.free)
                        .orElseThrow(() -> new IllegalArgumentException("Free plan not found"));
                User user = userPair.get();
                CreateSuscriptionsCommand command = new CreateSuscriptionsCommand(freePlan.getId(), user.getId(), null); // get the id of the User object
                try {
                    suscriptionsCommandService.handle(command);
                } catch (Exception e) {
                    System.out.println("Error al crear la suscripción: " + e.getMessage());
                    e.printStackTrace();
                }

                // Generate a new authentication token for the user
                String token = tokenService.generateToken(user.getEmail()); // Usa el método generateToken de TokenService

                // Create an AuthenticatedUserResource with the user's information and the new token
                AuthenticatedUserResource authenticatedUserResource = new AuthenticatedUserResource(user.getId(), token, user.getRole().getStringName());

                return ResponseEntity.status(CREATED).body(authenticatedUserResource);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            System.out.println("Error al crear el usuario: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id){
        Optional<User> user = userQueryService.handle(new GetUserByIdQuery(id));
        return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<List<UserResource>> getAllUsersByName(String name){

        var getAllUserByNameQuery = new GetAllUserByNameQuery(name);
        var users = userQueryService.handle(getAllUserByNameQuery);
        if (users.isEmpty()) return ResponseEntity.notFound().build();
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    @GetMapping(params = {"!name", "!id", "!email", "!password", "!role"})
    public ResponseEntity<List<UserResource>> getAllUsers(){
        var users = userQueryService.getAllUsers();
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    @GetMapping(params = "name")
    public ResponseEntity<?> getUsersWithParameters(@RequestParam Map<String, String> params){
        if (params.containsKey("name")) {
            return getAllUsersByName(params.get("name"));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long id, @RequestBody SignUpResource resource) {
        Optional<User> user = userCommandService.updateUser(id, SignUpCommandFromResourceAssembler.toCommandFromResource((resource)));
        return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(params = {"email", "password"})
    public ResponseEntity<UserResource> getUserByEmailAndPassword(@RequestParam("email") String email, @RequestParam("password") String password){
        Optional<User> user = userQueryService.getUserByEmailAndPassword(email, password);
        return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
