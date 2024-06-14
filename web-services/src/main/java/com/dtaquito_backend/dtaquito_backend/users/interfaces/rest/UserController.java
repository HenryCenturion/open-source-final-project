package com.dtaquito_backend.dtaquito_backend.users.interfaces.rest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.services.SuscriptionsCommandService;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.commands.CreateSuscriptionsCommand;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.queries.GetAllUserByNameQuery;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.queries.GetUserByIdQuery;
import com.dtaquito_backend.dtaquito_backend.users.domain.services.UserCommandService;
import com.dtaquito_backend.dtaquito_backend.users.domain.services.UserQueryService;
import com.dtaquito_backend.dtaquito_backend.users.interfaces.rest.resources.CreateUserResource;
import com.dtaquito_backend.dtaquito_backend.users.interfaces.rest.resources.UserResource;
import com.dtaquito_backend.dtaquito_backend.users.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.dtaquito_backend.dtaquito_backend.users.interfaces.rest.transform.UserResourceFromEntityAssembler;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final SuscriptionsCommandService suscriptionsCommandService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService, SuscriptionsCommandService suscriptionsCommandService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
        this.suscriptionsCommandService = suscriptionsCommandService;
    }

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource){
        Optional<User> user = userCommandService.handle(CreateUserCommandFromResourceAssembler.toCommandFromResource((resource)));
        if (user.isPresent()) {
            CreateSuscriptionsCommand command = new CreateSuscriptionsCommand("free", user.get().getId());
            suscriptionsCommandService.handle(command);
        }
        return user.map(source -> new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
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
    public ResponseEntity<UserResource> updateUser(@PathVariable Long id, @RequestBody CreateUserResource resource) {
        Optional<User> user = userCommandService.updateUser(id, CreateUserCommandFromResourceAssembler.toCommandFromResource((resource)));
        return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(params = {"email", "password"})
    public ResponseEntity<UserResource> getUserByEmailAndPassword(@RequestParam("email") String email, @RequestParam("password") String password){
        Optional<User> user = userQueryService.getUserByEmailAndPassword(email, password);
        return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
