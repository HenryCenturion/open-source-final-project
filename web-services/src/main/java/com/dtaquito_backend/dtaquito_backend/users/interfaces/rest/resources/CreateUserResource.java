package com.dtaquito_backend.dtaquito_backend.users.interfaces.rest.resources;

public record CreateUserResource(String name, String email, String password, String role) {
    public CreateUserResource {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
    }
}
