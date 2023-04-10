package com.store.OnlineShop;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.store.OnlineShop.service.DepartmentService;

@WebMvcTest(DepartmentService.class)
public class WebLayerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnSomething() throws Exception{
		this.mockMvc.perform(get("/Department")).andDo(print()).andExpect(status().isOk());
//		.andExpect(content().string(containsString("")));
		System.out.println("----");
	}

}
