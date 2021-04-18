package com.edenred.subscription;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SubscriptionApplicationTests {
@Autowired
private ApplicationContext applicationContext;
	@Test
	void contextLoads() {
		Assert.assertNotNull(applicationContext);
	}

}
