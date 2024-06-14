package com.dtaquito_backend.dtaquito_backend.sportspaces.interfaces.rest.resources;

import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;

public record SportSpacesResource(Long id, String name, String imageUrl, Long price, String description, User user, String StartTime, String endTime) {}
