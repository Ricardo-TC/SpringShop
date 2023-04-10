package com.store.OnlineShop.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmokeTest {
	
	@Autowired
	private PurchasingController controller;

	@Test
	public void testContextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
