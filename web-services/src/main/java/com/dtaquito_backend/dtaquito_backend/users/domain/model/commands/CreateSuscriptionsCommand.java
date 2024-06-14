package com.dtaquito_backend.dtaquito_backend.users.domain.model.commands;

public record CreateSuscriptionsCommand(String plan, Long userId) {

    public CreateSuscriptionsCommand {
        if (plan == null || plan.isBlank()) {
            throw new IllegalArgumentException("Plan is required");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID is required");
        }
    }
}
