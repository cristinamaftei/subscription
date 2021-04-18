package com.edenred.subscription.registration.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RegistrationResponse {
    private String firstName;
    private String lastName;
    private String email;
}
