package com.store.OnlineShop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.store.OnlineShop.model.entity.Cart;
import com.store.OnlineShop.service.PurchasingService;

@RestController
@RequestMapping("/Purchase")
@CrossOrigin(origins = "http://localhost:8080")
public class PurchasingController {
	
	private final PurchasingService purchasingService;
	
	public PurchasingController(PurchasingService purchasingService) {
		super();
		this.purchasingService = purchasingService;
	}
	
	//@ConditionalOnProperty("my.application")
	@PostMapping("/NewTicket")
	public int newTicket() {
		return this.purchasingService.newTicket();
	}
	
	@PostMapping("/Cart")
	public Cart addToCart(@RequestParam int prod_id,@RequestParam int quantity) {
		return this.purchasingService.addToCart(prod_id,quantity);
	}
	
	@GetMapping("{id}")
	public List<Cart> getCartByTicket(@PathVariable("id") int id){
		return this.purchasingService.getCartByTicket(id);
	}
	
	@PatchMapping("/FinishPurchasePrintTicket")
	public List<Cart> finishPurchasePrintTicket() {
		return this.purchasingService.totalPurchase();
	}
	
	@GetMapping("/GetTotal")
	public float onlyTotalPurchase() {
		return this.purchasingService.onlyTotalPurchase();
	}
	
	@DeleteMapping("/DeleteProductFromCart/{id}")
	public void deleteProductFromCart(@PathVariable("id") int id) {
		this.purchasingService.deleteProductFromCart(id);
	}
	
	@PatchMapping("/UpdateProductFromCart")
	public void updateProductFromCart(@RequestParam int id,@RequestParam int prod_id,@RequestParam int quantity) {
		this.purchasingService.updateProductFromCart(id, prod_id, quantity);
	}
}
