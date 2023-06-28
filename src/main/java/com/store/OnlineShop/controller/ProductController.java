package com.store.OnlineShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.store.OnlineShop.model.entity.Product;
import com.store.OnlineShop.service.ProductService;
import com.store.OnlineShop.service.dto.ProductInDTO;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/Product", method = RequestMethod.POST)
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
	
	@PatchMapping("/{id}")
	public void updateProduct(@PathVariable("id") int id,@RequestBody ProductInDTO productInDTO) {
		this.service.updateProduct(id, productInDTO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") int id) {
		this.service.deleteById(id);
	}
	
	/*
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/Product", method = RequestMethod.OPTIONS)
	public HttpStatus handleOptionsRequest() {
        // Return a successful HTTP status code (e.g., 200)
		return HttpStatus.OK;
    }
	*/
}
