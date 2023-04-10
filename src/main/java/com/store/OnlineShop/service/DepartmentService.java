package com.store.OnlineShop.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.store.OnlineShop.exceptions.ShopExceptions;
import com.store.OnlineShop.mapper.DepartmentDTOMapper;
import com.store.OnlineShop.model.entity.Department;
import com.store.OnlineShop.model.repository.DepartmentRepository;
import com.store.OnlineShop.service.dto.DepartmentInDTO;
import com.store.OnlineShop.util.Validations;

@Service
public class DepartmentService {
	private final DepartmentDTOMapper mapper;
	private final DepartmentRepository repository;
	private final Validations validations = new Validations();
	public DepartmentService(DepartmentDTOMapper mapper, DepartmentRepository repository) {
		super();
		this.mapper = mapper;
		this.repository = repository;
	}
	
	//@Autowired
	public Department createDepartment(DepartmentInDTO departmentInDTO) {
		validations.validateDepartment(departmentInDTO);
		Department department = mapper.map(departmentInDTO);
		return this.repository.save(department);
	}
	
	public List<Department> findAll(){
		return this.repository.findAll();
	}
	
	public Department findById(int id) {
		Optional<Department> opt = repository.findById(id);
		if(opt.isEmpty())throw new ShopExceptions("Data not found",HttpStatus.NOT_FOUND);
		return opt.get();
	}
	
	@Transactional
	public void updateDepartment(int id, DepartmentInDTO departmentInDTO) {
		validations.validateDepartment(departmentInDTO);
		if(repository.findById(id).isEmpty())throw new ShopExceptions("Data not found",HttpStatus.NOT_FOUND);
		this.repository.updateDepartment(id,departmentInDTO.getDep_name());
	}
	
	public void deleteById(int id) {
		if(repository.findById(id).isEmpty())throw new ShopExceptions("Data not found",HttpStatus.NOT_FOUND);
		this.repository.deleteById(id);
	}

}
