package com.dtaquito_backend.dtaquito_backend.users.interfaces.acl;

import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.dtaquito_backend.dtaquito_backend.users.domain.services.ExternalSystemHttpClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalSystemHttpClientImpl implements ExternalSystemHttpClient {

    private final RestTemplate restTemplate;

    public ExternalSystemHttpClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User getUserProfile(String userId) {
        String url = "http://external-system.com/api/users/" + userId;

        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to get user profile: " + response.getStatusCode());
        }

        return response.getBody();
    }

    @Override
    public void updateUserProfile(User userProfile) {
        String url = "http://external-system.com/api/users/" + userProfile.getId();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<User> entity = new HttpEntity<>(userProfile, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to update user profile: " + response.getStatusCode());
        }
    }
}