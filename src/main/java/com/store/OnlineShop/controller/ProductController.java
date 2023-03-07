package com.store.OnlineShop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.OnlineShop.model.entity.Product;
import com.store.OnlineShop.service.ProductService;
import com.store.OnlineShop.service.dto.ProductInDTO;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/Product")
public class ProductController {

	private final ProductService service;

	public ProductController(ProductService service) {
		super();
		this.service = service;
	}
	
	@PostMapping
	public Product createProduct(@RequestBody ProductInDTO productInDTO) {
		return this.service.createProduct(productInDTO);
	}
	
	@GetMapping
	public List<Product> findAll(){
		return this.service.findAll();
	}
	
	@GetMapping("/{id}")
	public Product findById(@PathVariable("id") int id) {
		return this.service.findById(id);
	}
}
