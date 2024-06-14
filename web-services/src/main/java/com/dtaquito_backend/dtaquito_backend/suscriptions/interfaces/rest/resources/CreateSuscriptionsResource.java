package com.dtaquito_backend.dtaquito_backend.suscriptions.interfaces.rest.resources;

public record CreateSuscriptionsResource(String plan, Long userId) {

    public CreateSuscriptionsResource {
        if (plan == null || plan.isBlank()) {
            throw new IllegalArgumentException("Plan is required");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID is required");
        }
    }
}
