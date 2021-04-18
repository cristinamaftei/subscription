package com.edenred.subscription;

import com.edenred.subscription.registration.model.Account;

import java.util.Date;

public class TestData {
    public static Account createAccount(){
        return Account.builder()
                .firstName("First")
                .lastName("Customer")
                .email("customer@customer.ro")
                .created(new Date())
                .build();
    }
}
