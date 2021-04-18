package com.edenred.subscription.registration;


import com.edenred.subscription.registration.dto.RegistrationRequest;
import com.edenred.subscription.registration.dto.RegistrationResponse;
import com.edenred.subscription.registration.dto.RegistrationStatusResponse;
import com.edenred.subscription.registration.model.Account;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RequestMapping("/registration")
@AllArgsConstructor
@RestController
@Slf4j
public class RegistrationController {
    private final RegistrationService registrationService;
    private final RegistrationConverter registrationConverter;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public RegistrationResponse register( @RequestBody @Valid RegistrationRequest registrationRequest) {
        Account account = registrationConverter.registrationRequestToAccount(registrationRequest);

        Account createdAccount = registrationService.registerAccount(account);

        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setEmail(createdAccount.getEmail());
        registrationResponse.setFirstName(createdAccount.getFirstName());
        registrationResponse.setLastName(createdAccount.getLastName());

        log.info("Registration response {} ", registrationResponse);
        return registrationResponse;
    }

    @GetMapping()
    @ResponseStatus(OK)
    public RegistrationStatusResponse isRegistered(@Valid @RequestParam("email") @NotEmpty String email) {

        return registrationService
                .getAccount(email)
                .map(account -> RegistrationStatusResponse.builder()
                        .isRegistered(true)
                        .firstName(account.getFirstName())
                        .lastName(account.getLastName())
                        .build())
                .orElse(RegistrationStatusResponse.builder().isRegistered(false).build());
    }
}
