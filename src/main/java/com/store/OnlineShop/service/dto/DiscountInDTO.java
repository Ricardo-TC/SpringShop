package com.store.OnlineShop.service.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class DiscountInDTO {

	private int prod_id;
	private String discount_type;
	private String discount_amount;
	private LocalDate date_begin;
	private LocalDate date_expire;
	
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
