package com.edenred.subscription;

import com.edenred.subscription.registration.AccountRepository;
import com.edenred.subscription.registration.RegistrationService;
import com.edenred.subscription.registration.exception.RegistrationException;
import com.edenred.subscription.registration.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService registrationService;
    @Mock
    private AccountRepository accountRepository;

    private final static String EMAIL = "customer@customer.ro";
    private Account account;
    @Before
    public void SetUp(){
        initMocks(this);
        account = TestData.createAccount();
    }

    @Test
    public void testRegisterAccount(){
        when(accountRepository.getAccountByEmail(EMAIL)).thenReturn(null);
        when(accountRepository.save(account)).thenReturn(account);
        Account actual = registrationService.registerAccount(account);

        verify(accountRepository).save(account);
        Assert.assertNotNull(actual);
        Assert.assertEquals(account.getEmail(), actual.getEmail());
        Assert.assertEquals(account.getFirstName(), actual.getFirstName());
        Assert.assertEquals(account.getLastName(), actual.getLastName());
    }

    @Test(expected = RegistrationException.class)
    public void testRegisterAccountAlreadyExists(){
        when(accountRepository.getAccountByEmail(EMAIL)).thenReturn(account);
        registrationService.registerAccount(account);
    }

    @Test
    public void testGetAccount(){
        when(accountRepository.getAccountByEmail(EMAIL)).thenReturn(account);
        Optional<Account> actual = registrationService.getAccount(EMAIL);
        Assert.assertEquals(EMAIL, actual.stream().map(account1 -> account1.getEmail()).findFirst().orElse(""));
    }

    @Test
    public void testGetAccount_noAccountFound(){
        when(accountRepository.getAccountByEmail(EMAIL)).thenReturn(null);
        Optional<Account> actual = registrationService.getAccount(EMAIL);
        Assert.assertTrue(actual.isEmpty());
    }
}
