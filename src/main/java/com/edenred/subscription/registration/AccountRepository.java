package com.edenred.subscription.registration;

import com.edenred.subscription.registration.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account getAccountByEmail(String email);

}
