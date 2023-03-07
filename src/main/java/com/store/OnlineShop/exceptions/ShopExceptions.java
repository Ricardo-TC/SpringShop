package com.store.OnlineShop.exceptions;

import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class ShopExceptions extends RuntimeException{
	
	private String message;
	private HttpStatus httpStatus;
	public ShopExceptions(String message, HttpStatus httpStatus) {
		super(message);
		this.message = message;
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
