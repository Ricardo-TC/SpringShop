package com.store.OnlineShop.service.dto;

import lombok.Data;

@Data
public class DepartmentInDTO {
	
	private String dep_name;

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}
	
	

}
