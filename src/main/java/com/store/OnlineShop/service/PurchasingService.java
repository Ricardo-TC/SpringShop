package com.store.OnlineShop.service;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.store.OnlineShop.exceptions.ShopExceptions;
import com.store.OnlineShop.mapper.CartDTOMapper;
import com.store.OnlineShop.model.entity.Cart;
import com.store.OnlineShop.model.entity.Product;
import com.store.OnlineShop.model.repository.CartRepository;
import com.store.OnlineShop.model.repository.ProductRepository;
import com.store.OnlineShop.model.repository.TicketRepository;
import com.store.OnlineShop.service.dto.CartInDTO;
import com.store.OnlineShop.util.Validations;

@Service
public class PurchasingService {

	private final CartDTOMapper cartMapper;
	private final CartRepository cartRepository;
	private final TicketRepository ticketRepository;
	private final ProductRepository productRepository;
	private final Validations validations = new Validations();
	
	private int lastTicket;
	
	
	public PurchasingService(CartDTOMapper cartMapper, CartRepository cartRepository,
			TicketRepository ticketRepository,ProductRepository productRepository) {
		super();
		this.cartMapper = cartMapper;
		this.cartRepository = cartRepository;
		this.ticketRepository = ticketRepository;
		this.productRepository = productRepository;
	}
	
	public int newTicket() {
		lastTicket = this.ticketRepository.newTicket();
		return lastTicket;
	}
	
	public Cart addToCart(int prod_id,int quantity) {
		if(!validations.validateNumber(lastTicket))throw new ShopExceptions("It is required a new ticket",HttpStatus.BAD_REQUEST);
		if(!validations.validateNumber(prod_id))throw new ShopExceptions("Product is required",HttpStatus.NO_CONTENT);
		if(!validations.validateNumber(quantity))throw new ShopExceptions("Quantity is required",HttpStatus.NO_CONTENT);
        Product product = this.productRepository.getById(prod_id);
		float subtotal = quantity*product.getPrice();
		CartInDTO cartInDTO = new CartInDTO(lastTicket,prod_id,quantity,subtotal);
		Cart cart = cartMapper.map(cartInDTO);
		return this.cartRepository.save(cart);
	}
	
	@Transactional
	public float totalPurchase() {
		if(!validations.validateNumber(lastTicket))throw new ShopExceptions("It is required a new ticket",HttpStatus.BAD_REQUEST);
		return this.ticketRepository.totalPurchase(lastTicket);
	}
	
		
	
}
