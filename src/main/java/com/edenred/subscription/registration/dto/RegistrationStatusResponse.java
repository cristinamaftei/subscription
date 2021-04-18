package com.edenred.subscription.registration.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationStatusResponse {
    private final boolean isRegistered;
    private final String firstName;
    private final String lastName;
}
