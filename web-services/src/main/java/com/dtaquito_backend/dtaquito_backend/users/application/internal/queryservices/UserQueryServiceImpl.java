package com.dtaquito_backend.dtaquito_backend.users.application.internal.queryservices;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.aggregates.Suscriptions;
import com.dtaquito_backend.dtaquito_backend.suscriptions.infrastructure.persistance.jpa.SuscriptionsRepository;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.queries.GetAllUserByNameQuery;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.queries.GetUserByIdQuery;
import com.dtaquito_backend.dtaquito_backend.users.domain.services.UserQueryService;
import com.dtaquito_backend.dtaquito_backend.users.infrastructure.persistance.jpa.UserRepository;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final SuscriptionsRepository subscriptionRepository;

    public UserQueryServiceImpl(UserRepository userRepository, SuscriptionsRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public List<User> handle (GetAllUserByNameQuery query) {
        return userRepository.findAllByName(query.name());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public String getUserRoleByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(u -> u.getRole().getRoleType().name()).orElse(null);
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        Long userId = query.id();
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new NoSuchElementException("User with ID " + userId + " not found");
        }
        return user;
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return handle(new GetUserByIdQuery(userId));
    }

    @Override
    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}