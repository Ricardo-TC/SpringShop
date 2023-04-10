package com.store.OnlineShop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.OnlineShop.mapper.DepartmentDTOMapper;
import com.store.OnlineShop.model.entity.Department;
import com.store.OnlineShop.model.repository.DepartmentRepository;
import com.store.OnlineShop.service.dto.DepartmentInDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OnlineShopDepartmentTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
//	@MockBean
	private DepartmentRepository depRepository;
	
	@Autowired
	private DepartmentDTOMapper depMapper;
	
	@Test
	public void registrationDeparment() throws Exception{
		DepartmentInDTO departmentInDTO = new DepartmentInDTO();
		departmentInDTO.setDep_name("testeo5");
		
		mockMvc.perform(post("/Department")
				.contentType("application/json")
				.param("name", "true")
				.content(objectMapper.writeValueAsString(departmentInDTO)))
				.andExpect(status().isOk());
		
		Department department = depRepository.getByName("testeo5");
		assertThat(department.getDep_name()).isEqualTo("testeo5");
		System.out.println("------------------------------------------------------------------------------1");
		
	}

	@Test
	public void shouldReturnOkDepartment() throws Exception {
		this.mockMvc.perform(get("/Department")).andDo(print()).andExpect(status().isOk());
		System.out.println("------------------------------------------------------------------------------2");
	}

	@Test
	public void shouldReturnBad() throws Exception{
		
		this.mockMvc.perform(get("/Department/666"))
			.andDo(print())
			.andExpect(status().is(404));
		System.out.println("------------------------------------------------------------------------------3");
	}

}
