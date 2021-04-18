package com.edenred.subscription.registration;


import com.edenred.subscription.registration.dto.RegistrationRequest;
import com.edenred.subscription.registration.model.Account;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegistrationConverter {

    public Account registrationRequestToAccount(RegistrationRequest registrationRequest) {
        return Account.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .created(new Date())
                .build();
    }
}
