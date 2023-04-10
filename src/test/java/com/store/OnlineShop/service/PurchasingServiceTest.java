package com.store.OnlineShop.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.store.OnlineShop.model.entity.Cart;

class PurchasingServiceTest {

	@Test
	public void testGetSubtotal() {
		final PurchasingService service = new PurchasingService(null, null, null, null, null);
		int ticket = service.newTicket();
		Cart cart = service.addToCart(1,1);
//		assertEquals(13.05,service.getSubtotal(1, 1));
//		fail("Not yet implemented");
	}

}
