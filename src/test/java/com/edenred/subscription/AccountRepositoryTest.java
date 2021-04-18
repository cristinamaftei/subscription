package com.edenred.subscription;

import com.edenred.subscription.registration.AccountRepository;
import com.edenred.subscription.registration.model.Account;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest{
    @Autowired
    private AccountRepository accountRepository;

    private Account account;
    @Before
    public void setUp(){
       account = Account.builder()
               .firstName("First")
               .lastName("Customer")
               .email("customer@customer.ro")
               .created(new Date())
               .build();
       accountRepository.save(account);
    }

    @After
    public void tearDown(){
        accountRepository.delete(account);
    }

    @Test
    public void testGetAccountByEmail(){
        Account actual = accountRepository.getAccountByEmail("customer@customer.ro");
        Assert.assertNotNull(actual);
        Assert.assertEquals("First", actual.getFirstName());
        Assert.assertEquals("Customer", actual.getLastName());
    }
}
