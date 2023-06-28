package com.store.OnlineShop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.OnlineShop.model.entity.Department;
import com.store.OnlineShop.service.DepartmentService;
import com.store.OnlineShop.service.dto.DepartmentInDTO;

@RestController
@RequestMapping(/*value = */"/Department")
@CrossOrigin(origins = "http://localhost:8080")
public class DepartmentController {
	private final DepartmentService service;

	public DepartmentController(DepartmentService service) {
		super();
		this.service = service;
	}
	
//	@PostMapping(produces = "application/json" , consumes = "application/json")
//	public Department createDepartment(@RequestBody DepartmentInDTO departmentInDTO) {
//		return this.service.createDepartment(dep_name);
//	}
	
    @PostMapping(path = "/createDepartment", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentInDTO departmentInDTO) {
        String dep_name = departmentInDTO.getDep_name();
        Department createdDepartment = this.service.createDepartment(dep_name);
        return ResponseEntity.ok(createdDepartment);
    }


	
	@GetMapping
	public List<Department> findAll(){
		return this.service.findAll();
	}
	
	@GetMapping("/{id}")
	public Department findById(@PathVariable("id") int id) {
		return this.service.findById(id);
	}
	
	@PatchMapping("/{id}")
	public void updateById(@PathVariable("id") int id, @RequestBody DepartmentInDTO departmentInDTO) {
		this.service.updateDepartment(id, departmentInDTO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") int id) {
		this.service.deleteById(id);
	}

}
