package com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.commands;

public record CreateSportSpacesCommand(String name, String imageUrl, Long price, String description, Long userId, String startTime, String endTime) {

    public CreateSportSpacesCommand {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("Image URL is required");
        }
        if (price == null) {
            throw new IllegalArgumentException("Price is required");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID is required");
        }
        if (startTime == null || startTime.isBlank()) {
            throw new IllegalArgumentException("Start time is required");
        }
        if (endTime == null || endTime.isBlank()) {
            throw new IllegalArgumentException("End time is required");
        }
    }
}