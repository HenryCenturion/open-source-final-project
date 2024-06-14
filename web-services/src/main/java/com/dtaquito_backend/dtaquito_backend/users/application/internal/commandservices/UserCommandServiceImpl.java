package com.dtaquito_backend.dtaquito_backend.users.application.internal.commandservices;

import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.commands.CreateUserCommand;
import com.dtaquito_backend.dtaquito_backend.users.domain.services.UserCommandService;
import com.dtaquito_backend.dtaquito_backend.users.infrastructure.persistance.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        if (userRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("User with name and id already exists");
        }
        var user = new User(command);
        var createdUser = userRepository.save(user);
        return Optional.of(createdUser);
    }

    @Override
    public Optional<User> updateUser(Long id, CreateUserCommand command) {
        var user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.update(command);
        var updatedUser = userRepository.save(user);
        return Optional.of(updatedUser);
    }

}
