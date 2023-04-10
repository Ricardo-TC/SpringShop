package com.store.OnlineShop;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.OnlineShop.model.entity.Ticket;
import com.store.OnlineShop.model.repository.TicketRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class OnlineShopTicketTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private TicketRepository repository;

	@Test
	public void ticketFailTest() throws Exception{
		this.mockMvc.perform(get("/Product/1")).andDo(print()).andExpect(status().is(200));
		System.out.println("Error---------------------------------------------------------1");
	}
	
//	@Test
//	public void ticketConnectionTest() throws Exception{
//		this.mockMvc.perform(get("/Purchase")).andDo(print()).andExpect(status().isOk());
//		System.out.println("Connection---------------------------------------------------------2");
//	}
	
//	@Test
//	public void ticketResgistrationTest() throws Exception{
//		Ticket ticket = new Ticket();
//		ticket.setTicket_id(5);
//		ticket.setTotal(13);
//		ticket.setDate(LocalDate.now());
//		
//		mockMvc.perform(post("/Product")
//				.contentType("application/json")
//				.param("name", "true")
//				.content(objectMapper.writeValueAsString(ticket)))
//				.andExpect(status().isOk());
//		System.out.println("Resgistration---------------------------------------------------------3");
//		
//		
//		
//	}

}
