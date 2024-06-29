package com.dtaquito_backend.dtaquito_backend.users.application.internal.outboundservices.acl;

import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.dtaquito_backend.dtaquito_backend.users.domain.services.ExternalSystemHttpClient;
import org.springframework.stereotype.Service;

@Service
public class ExternalProfileService {

    private final ExternalSystemHttpClient httpClient;

    public ExternalProfileService(ExternalSystemHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public User getExternalUserProfile(String userId) {
        return httpClient.getUserProfile(userId);
    }

    public void updateExternalUserProfile(User userProfile) {
        httpClient.updateUserProfile(userProfile);
    }
}