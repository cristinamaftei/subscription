package com.edenred.subscription;

import com.edenred.subscription.registration.RegistrationController;
import com.edenred.subscription.registration.RegistrationConverter;
import com.edenred.subscription.registration.RegistrationService;
import com.edenred.subscription.registration.dto.RegistrationRequest;
import com.edenred.subscription.registration.dto.RegistrationResponse;
import com.edenred.subscription.registration.dto.RegistrationStatusResponse;
import com.edenred.subscription.registration.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RegistrationControllerTest {
    @InjectMocks
    private RegistrationController registrationController;
    @Mock
    private RegistrationService registrationService;
    @Mock
    RegistrationConverter registrationConverter;

    private final static String EMAIL = "customer@customer.ro";
    private Account account;

    @Before
    public void setUp(){
        initMocks(this);
        account = TestData.createAccount();
    }

    @Test
    public void testRegister(){
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmail(EMAIL);
        registrationRequest.setFirstName("First");
        registrationRequest.setLastName("Customer");
        when(registrationConverter.registrationRequestToAccount(registrationRequest)).thenReturn(account);
        when(registrationService.registerAccount(account)).thenReturn(account);

        RegistrationResponse response = registrationController.register(registrationRequest);
        Assert.assertNotNull(response);
        Assert.assertEquals(registrationRequest.getEmail(), response.getEmail());
        Assert.assertEquals(registrationRequest.getFirstName(), response.getFirstName());
        Assert.assertEquals(registrationRequest.getLastName(), response.getLastName());
    }

    @Test
    public void testIsRegistered(){
        when(registrationService.getAccount(EMAIL)).thenReturn(Optional.of(account));
        RegistrationStatusResponse response = registrationController.isRegistered(EMAIL);
        Assert.assertTrue(response.isRegistered());
    }

    @Test
    public void testIsRegistered_notTrue(){
        when(registrationService.getAccount(EMAIL)).thenReturn(Optional.empty());
        RegistrationStatusResponse response = registrationController.isRegistered(EMAIL);
        Assert.assertFalse(response.isRegistered());
    }
}
