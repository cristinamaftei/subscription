package com.edenred.subscription.registration;

import com.edenred.subscription.registration.exception.RegistrationException;
import com.edenred.subscription.registration.model.Account;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {
    private final AccountRepository accountRepository;

    public Account registerAccount(Account account) {
        if (nonNull(accountRepository.getAccountByEmail(account.getEmail()))) {
            throw new RegistrationException(String.format("Account with email  %s already exists", account.getEmail()));
        }
        log.info("Created account with email {} ", account.getEmail());
        return  accountRepository.save(account);
    }

    public Optional<Account> getAccount(String email) {
        return Optional.ofNullable(accountRepository.getAccountByEmail(email));
    }
}
