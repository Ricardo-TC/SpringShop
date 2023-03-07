package com.store.OnlineShop.service.dto;


import lombok.Data;

@Data
public class CartInDTO {

	private int prod_id;
	private int quantity;
	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
