package com.dtaquito_backend.dtaquito_backend.sportspaces.application.internal.commandservices;

import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.aggregates.SportSpaces;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.commands.CreateSportSpacesCommand;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.services.SportSpacesCommandService;
import com.dtaquito_backend.dtaquito_backend.sportspaces.infrastructure.persistance.jpa.SportSpacesRepository;
import com.dtaquito_backend.dtaquito_backend.users.infrastructure.persistance.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SportSpacesCommandServiceImpl implements SportSpacesCommandService {

    private final SportSpacesRepository sportSpacesRepository;
    private final UserRepository userRepository;

    public SportSpacesCommandServiceImpl(SportSpacesRepository sportSpacesRepository, UserRepository userRepository) {
        this.sportSpacesRepository = sportSpacesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<SportSpaces> handle(Long userId, CreateSportSpacesCommand command) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        var sportSpaces = new SportSpaces(command, user);
        var createdSportSpaces = sportSpacesRepository.save(sportSpaces);
        return Optional.of(createdSportSpaces);
    }

    @Override
    public Optional<SportSpaces> handleUpdate(Long id, CreateSportSpacesCommand command) {
        var sportSpaces = sportSpacesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SportSpaces not found"));
        sportSpaces.update(command);
        var updatedSportSpaces = sportSpacesRepository.save(sportSpaces);
        return Optional.of(updatedSportSpaces);
    }

    @Override
    public void handleDelete(Long id) {
        sportSpacesRepository.deleteById(id);
    }
}