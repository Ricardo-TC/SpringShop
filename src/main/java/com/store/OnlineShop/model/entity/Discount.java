package com.store.OnlineShop.model.entity;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "discount")
public class Discount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "disc_id")
	private int disc_id;
	@Column(name = "prod_id")
	private int prod_id;
	@Column(name = "discount_type", nullable = true)
	private String discount_type;
	@Column(name = "discount_amount", nullable = true)
	private String discount_amount;
	@Column(name = "date_begin")
	private LocalDate date_begin;
	@Column(name = "date_expire")
	private LocalDate date_expire;
	public int getDisc_id() {
		return disc_id;
	}
	public void setDisc_id(int disc_id) {
		this.disc_id = disc_id;
	}
	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public String getDiscount_type() {
		return discount_type;
	}
	public void setDiscount_type(String discount_type) {
		this.discount_type = discount_type;
	}
	public String getDiscount_amount() {
		return discount_amount;
	}
	public void setDiscount_amount(String discount_amount) {
		this.discount_amount = discount_amount;
	}
	public LocalDate getDate_begin() {
		return date_begin;
	}
	public void setDate_begin(LocalDate date_begin) {
		this.date_begin = date_begin;
	}
	public LocalDate getDate_expire() {
		return date_expire;
	}
	public void setDate_expire(LocalDate date_expire) {
		this.date_expire = date_expire;
	}
	

}
