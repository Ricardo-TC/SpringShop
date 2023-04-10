package com.store.OnlineShop.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.store.OnlineShop.service.PurchasingService;

@WebMvcTest(PurchasingController.class)
public class PurchasingControllerTest {

	@MockBean
	PurchasingController controller;
	
	@MockBean
	PurchasingService service;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testNewTicketShouldReturnInteger() throws Exception{
		when(service.newTicket()).thenReturn(5);
		this.mockMvc.perform(get("/NewTicket")).andDo(print()).andExpect(status().isOk());
//		.andExpect(content().string(containsString("sup")));
	}
	
	
	
//	@Test
//	void testPurchasingController() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testNewTicket() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAddToCart() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testTotalPurchase() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testSum() {
//		assertEquals(6,controller.sum(2,3));
//			fail("Not yet implemented");
//	}

}
