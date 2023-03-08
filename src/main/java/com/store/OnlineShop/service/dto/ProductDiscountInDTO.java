package com.store.OnlineShop.service.dto;

import lombok.Data;

@Data
public class ProductDiscountInDTO {
	
	private String discount_type;
	private String discount_amount;
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
	
	

}
