package com.dtaquito_backend.dtaquito_backend.suscriptions.interfaces.rest.resources;

import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;

public record SuscriptionsResource(Long id, String plan, User user) {
}
