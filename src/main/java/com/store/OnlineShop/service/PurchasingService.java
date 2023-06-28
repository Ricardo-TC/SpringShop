package com.store.OnlineShop.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.store.OnlineShop.exceptions.ShopExceptions;
import com.store.OnlineShop.mapper.CartDTOMapper;
import com.store.OnlineShop.model.entity.Cart;
import com.store.OnlineShop.model.entity.Discount;
import com.store.OnlineShop.model.entity.Product;
import com.store.OnlineShop.model.repository.CartRepository;
import com.store.OnlineShop.model.repository.DiscountRepository;
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
	private final DiscountRepository discountRepository;
	private final Validations validations = new Validations();
	
	private int ticket;
	
	
	public PurchasingService(CartDTOMapper cartMapper, CartRepository cartRepository,
			TicketRepository ticketRepository,ProductRepository productRepository,DiscountRepository discountRepository) {
		super();
		this.cartMapper = cartMapper;
		this.cartRepository = cartRepository;
		this.ticketRepository = ticketRepository;
		this.productRepository = productRepository;
		this.discountRepository = discountRepository; 
	}
	
	public int newTicket() {
		ticket = this.ticketRepository.newTicket();
		return ticket;
	}
	
	public Cart addToCart(int prod_id,int quantity) {
		if(ticket==0)throw new ShopExceptions("Please generate new ticket",HttpStatus.BAD_REQUEST);
		if(!validations.validateNumber(prod_id))throw new ShopExceptions("Product is required",HttpStatus.NO_CONTENT);
		if(!validations.validateNumber(quantity))throw new ShopExceptions("Quantity is required",HttpStatus.NO_CONTENT);
		
		float subtotal = getSubtotal(prod_id,quantity);
		
		CartInDTO cartInDTO = new CartInDTO(ticket,prod_id,quantity,subtotal);
		Cart cart = cartMapper.map(cartInDTO);
		return this.cartRepository.save(cart);
	}
	
	public List<Cart> getCartByTicket(int ticket_id){
		return this.cartRepository.getCartByTicket(ticket_id);
	}
	
	@Transactional
	public List<Cart> totalPurchase() {
		if(!validations.validateNumber(ticket))throw new ShopExceptions("It is required a new ticket",HttpStatus.BAD_REQUEST);
		int tempTicket = ticket;
		Cart cart;
		ticket = 0;
		float total = this.ticketRepository.totalPurchase(tempTicket);
		return this.cartRepository.getCartByTicket(tempTicket);

	}
	
	public float onlyTotalPurchase() {
		if(!validations.validateNumber(ticket))throw new ShopExceptions("It is required a new ticket",HttpStatus.BAD_REQUEST);
		return this.ticketRepository.onlyTotalPurchase(ticket);
	}
	
	
	public void deleteProductFromCart(int id) {
		if(cartRepository.findById(id).isEmpty())throw new ShopExceptions("Data not found",HttpStatus.NOT_FOUND);
		this.cartRepository.deleteById(id);
	}
	
	@Transactional
	public void updateProductFromCart(int  id,int prod_id,int quantity) {
		if(!validations.validateNumber(ticket))throw new ShopExceptions("Problem with ticket",HttpStatus.BAD_REQUEST);
		if(!validations.validateNumber(prod_id))throw new ShopExceptions("Product is required",HttpStatus.NO_CONTENT);
		if(!validations.validateNumber(id))throw new ShopExceptions("Cart ID is required",HttpStatus.NO_CONTENT);
		if(!validations.validateNumber(quantity))throw new ShopExceptions("Quantity is required",HttpStatus.NO_CONTENT);
		float subtotal = getSubtotal(prod_id,quantity);
		this.cartRepository.updateProductFromCart(quantity, id, ticket, subtotal);
	}
	
	
	private float getSubtotal(int prod_id,int quantity) {
        Product product = this.productRepository.getById(prod_id);
        Discount discount = this.discountRepository.getActiveDiscount(prod_id, LocalDate.now());
        String discType = "";
        String discAmount = "";
        if(discount != null) {
        	discType = discount.getDiscount_type();
        	discAmount = discount.getDiscount_amount();
        }
        
        float productPrice = product.getPrice();
        float subtotal=0;
        
        switch(discType) {
        	case "d"://discount by percent
        		float amount = Integer.parseInt(discAmount);
        		amount = amount / 100;
        		subtotal = (productPrice*quantity)-(amount*productPrice);
        		break;
        	case "x"://discount by quantity e.g 2x1
        		int promotion1 = Integer.parseInt(String.valueOf(discAmount.charAt(0)));
        		int promotion2 = Integer.parseInt(String.valueOf(discAmount.charAt(2)));
        		int prodWOD = quantity / promotion1;
        		int prodToCharge = quantity - ((promotion1 - promotion2) * prodWOD);
        		subtotal = prodToCharge * productPrice;
        		break;
        	default://no discount calculated
        		subtotal = productPrice*quantity;
        		break;
        }
		return subtotal;
	}		
	
	private void printTicket() {
		
	}
	
}
