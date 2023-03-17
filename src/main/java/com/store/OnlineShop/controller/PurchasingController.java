package com.store.OnlineShop.controller;

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
	
	@PatchMapping("/TotalPurchase")
	public float totalPurchase() {
		return this.purchasingService.totalPurchase();
	}

}
