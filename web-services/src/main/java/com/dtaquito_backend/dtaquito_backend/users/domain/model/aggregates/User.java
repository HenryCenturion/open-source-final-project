package com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates;


import com.dtaquito_backend.dtaquito_backend.iam.domain.model.commands.SignUpCommand;
import com.dtaquito_backend.dtaquito_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.entities.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import com.dtaquito_backend.dtaquito_backend.users.domain.model.commands.CreateUserCommand;

import lombok.Getter;

@Entity
@Getter
public class User extends AuditableAbstractAggregateRoot<User> {

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User() {
    }

    public User(CreateUserCommand command, Role role) {
        this.name = command.name();
        this.email = command.email();
        this.password = command.password();
        this.role = role;
    }

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void update(SignUpCommand command) {
        this.name = command.name();
        this.email = command.email();
        this.password = command.password();
    }
}

