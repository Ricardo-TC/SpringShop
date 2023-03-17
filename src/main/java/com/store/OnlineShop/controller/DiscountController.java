package com.store.OnlineShop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.store.OnlineShop.model.entity.Discount;
import com.store.OnlineShop.service.DiscountService;
import com.store.OnlineShop.service.dto.DiscountInDTO;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/Discount")
public class DiscountController {
	
	private final DiscountService service;
	
	public DiscountController(DiscountService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public Discount setNewDiscount(@RequestBody DiscountInDTO discountInDTO) {
		return this.service.setNewDiscount(discountInDTO);
	}
	
	@GetMapping
	public List<Discount> findAll(){
		return this.service.findAll();
	}
	
	@GetMapping("/{disc_id}")
	public Discount fidnById(@PathVariable("disc_id") int disc_id) {
		return this.service.findById(disc_id);
	}
	
	@GetMapping("/DiscountByProduct/{prod_id}")
	public List<Discount> getDiscountByProduct(@PathVariable("prod_id") int prod_id){
		return this.service.getDiscountByProduct(prod_id);
	}
	
	@GetMapping("/DiscountByDate/{prod_id}")
	public Discount getActiveDiscount(@PathVariable("prod_id") int prod_id) {
		return this.service.getActiveDiscount(prod_id);
	}
	
	@PatchMapping("/{disc_id}")
	public void updateDiscount(@PathVariable("disc_id") int disc_id,@RequestBody DiscountInDTO discountInDTO) {
		this.service.updateDiscount(disc_id, discountInDTO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") int id) {
		this.service.deleteById(id);
	}
	
}
