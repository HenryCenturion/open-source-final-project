package com.dtaquito_backend.dtaquito_backend.suscriptions.application.internal.commandservices;

import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.aggregates.Suscriptions;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.commands.CreateSuscriptionsCommand;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.services.SuscriptionsCommandService;
import com.dtaquito_backend.dtaquito_backend.suscriptions.infrastructure.persistance.jpa.SuscriptionsRepository;
import com.dtaquito_backend.dtaquito_backend.users.infrastructure.persistance.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuscriptionsCommandServiceImpl implements SuscriptionsCommandService {

    private final SuscriptionsRepository suscriptionsRepository;
    private final UserRepository userRepository;

    public SuscriptionsCommandServiceImpl(SuscriptionsRepository suscriptionsRepository, UserRepository userRepository) {
        this.suscriptionsRepository = suscriptionsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Suscriptions> handle(CreateSuscriptionsCommand command) {
        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        var suscriptions = new Suscriptions(command, user);
        var createdSuscriptions = suscriptionsRepository.save(suscriptions);
        return Optional.of(createdSuscriptions);
    }

    @Override
    public Optional<Suscriptions> updateSuscription(Suscriptions suscription) {
        var updatedSuscription = suscriptionsRepository.save(suscription);
        return Optional.of(updatedSuscription);
    }
}
