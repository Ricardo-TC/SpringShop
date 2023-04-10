package com.store.OnlineShop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.OnlineShop.model.entity.Product;
import com.store.OnlineShop.model.repository.ProductRepository;
import com.store.OnlineShop.service.dto.ProductInDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class OnlineShopProductTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ProductRepository repository;
	
	@Test
	void testFailProduct() throws Exception{
		this.mockMvc.perform(get("/Product/4444"))
		.andDo(print())
		.andExpect(status().is(404));
		System.out.println("------------------------------------------------------------------------------1");
	}
	
	@Test
	public void shouldReturnOkProduct() throws Exception {
		this.mockMvc.perform(get("/Product")).andDo(print()).andExpect(status().isOk());
		System.out.println("------------------------------------------------------------------------------2");
	}
	
	@Test
	public void testRegistrationProduct() throws Exception{
		ProductInDTO productInDTO = new ProductInDTO();
		productInDTO.setName("Product5");
		productInDTO.setPrice(20);
		productInDTO.setCost(10);
		productInDTO.setDescription("Description product");
		productInDTO.setDep_id(1);
		
		mockMvc.perform(post("/Product")
				.contentType("application/json")
				.param("name", "true")
				.content(objectMapper.writeValueAsString(productInDTO)))
				.andExpect(status().isOk());
		
		Product product = this.repository.testGetProduct("Product5");
		assertThat(product.getName()).isEqualTo("Product5");
		System.out.println("------------------------------------------------------------------------------3");
		
	}

}
