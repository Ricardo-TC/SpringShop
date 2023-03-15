package com.store.OnlineShop.service.dto;


import lombok.Data;

@Data
public class CartInDTO {

	private int ticket_id;
	private int prod_id;
	private int quantity;
	private float subtotal;
	public CartInDTO(int ticket_id, int prod_id, int quantity, float subtotal) {
		super();
		this.ticket_id = ticket_id;
		this.prod_id = prod_id;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
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
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
}
