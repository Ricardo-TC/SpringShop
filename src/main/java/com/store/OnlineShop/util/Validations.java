package com.store.OnlineShop.util;

import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;

import com.store.OnlineShop.exceptions.ShopExceptions;
import com.store.OnlineShop.service.dto.CartInDTO;
import com.store.OnlineShop.service.dto.DepartmentInDTO;
import com.store.OnlineShop.service.dto.ProductInDTO;

public class Validations {
	private Pattern pattern = Pattern.compile("[^\\w\\s$]");
	public boolean validateString(String string) {
		if(string.isEmpty())return false;
		//System.out.println("------"+pattern.matcher(string).find());
		if(pattern.matcher(string).find())return false;//[a-zA-Z]+
		return true;
	}
	
	//agregar una validacion para tama;o de disctype y discamount
	
	public boolean validateNumber(Integer number) {
		String chain = String.valueOf(number);
		if(number==0 || chain.isBlank())return false;
		try{
			Integer.parseInt(chain);
		}catch(Exception e) {
			e.getMessage();
			e.toString();
		}
		return true;
	}
	
	public boolean validateFloat(Float number) {
		String chain = String.valueOf(number);
		if(number==0 || chain.isBlank())return false;
		try{
			//if (chain.matches("[-+]?[0-9]*\\.?[0-9]+")) 
			Float.parseFloat(chain);
		}catch(Exception e) {
			e.getMessage();
			e.toString();
		}
		return true;
	}
	
	public void validateProduct(ProductInDTO product) {
		if(!validateString(product.getName()))throw new ShopExceptions("Invalid name",HttpStatus.NOT_ACCEPTABLE);
		if(!validateFloat(product.getCost()))throw new ShopExceptions("Invalid cost",HttpStatus.NOT_ACCEPTABLE);
		if(!validateFloat(product.getPrice()))throw new ShopExceptions("Invalid price",HttpStatus.NOT_ACCEPTABLE);
		if(!product.getDescription().isEmpty())
			if(!validateString(product.getDescription()))throw new ShopExceptions("Invalid description",HttpStatus.NOT_ACCEPTABLE);
		if(!validateNumber(product.getDep_id()))throw new ShopExceptions("Invalid deparment id",HttpStatus.NOT_ACCEPTABLE);
	}
	
	public void validateDepartment(DepartmentInDTO department) {
		if(!validateString(department.getDep_name()))throw new ShopExceptions("Invalid department name",HttpStatus.NOT_ACCEPTABLE);
	}
	
	public void validateCart(CartInDTO cart) {
		if(!validateNumber(cart.getProd_id()))throw new ShopExceptions("Invalid product id",HttpStatus.NOT_ACCEPTABLE);
		if(!validateNumber(cart.getQuantity()))throw new ShopExceptions("Invalid quantity",HttpStatus.NOT_ACCEPTABLE);
	}
}
