package com.dtaquito_backend.dtaquito_backend.users.domain.services;

import java.util.Optional;

import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.commands.CreateUserCommand;

public interface UserCommandService {

    Optional<User> handle(CreateUserCommand command);

    Optional<User> updateUser(Long id, CreateUserCommand command);
}
