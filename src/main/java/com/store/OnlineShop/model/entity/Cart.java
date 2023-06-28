package com.store.OnlineShop.model.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
//import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "cart")
//@Table
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private int id;
	@Column(name = "ticket_id")
	private int ticket_id;
	@Column(name = "prod_id")
	private int prod_id;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "subtotal")
	private float subtotal;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
