package com.store.OnlineShop.mapper;

import org.springframework.stereotype.Component;

import com.store.OnlineShop.model.entity.Cart;
import com.store.OnlineShop.service.dto.CartInDTO;

@Component
public class CartDTOMapper implements IMapper<CartInDTO,Cart>{

	@Override
	public Cart map(CartInDTO in) {
		Cart cart = new Cart();
		cart.setTicket_id(in.getTicket_id());
		cart.setProd_id(in.getProd_id());
		cart.setQuantity(in.getQuantity());
		cart.setSubtotal(in.getSubtotal());
		return cart;
	}

}
