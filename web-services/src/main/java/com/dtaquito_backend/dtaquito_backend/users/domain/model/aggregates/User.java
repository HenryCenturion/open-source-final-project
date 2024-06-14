package com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates;


import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.dtaquito_backend.dtaquito_backend.users.domain.model.commands.CreateUserCommand;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractAggregateRoot<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String name;

    @Column(nullable = false)
    @Getter
    private String email;

    @Column(nullable = false)
    @Getter
    private String password;

    @Column(nullable = false)
    @Getter
    private String role;

    protected User() {
    }

    public User(CreateUserCommand command) {
        this.name = command.name();
        this.email = command.email();
        this.password = command.password();
        this.role = command.role();
    }

    public void update(CreateUserCommand command) {
        this.name = command.name();
        this.email = command.email();
        this.password = command.password();
    }
}

