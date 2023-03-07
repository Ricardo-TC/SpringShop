package com.store.OnlineShop.mapper;

import com.store.OnlineShop.model.entity.Department;
import com.store.OnlineShop.service.dto.DepartmentInDTO;

public class DepartmentDTOMapper implements IMapper<DepartmentInDTO,Department>{

	@Override
	public Department map(DepartmentInDTO in) {
		Department department = new Department();
		department.setDep_name(in.getDep_name());
		return department;
	}

}
