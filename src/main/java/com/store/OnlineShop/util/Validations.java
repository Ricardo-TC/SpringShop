package com.store.OnlineShop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.time.*;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import com.store.OnlineShop.exceptions.ShopExceptions;
import com.store.OnlineShop.service.dto.CartInDTO;
import com.store.OnlineShop.service.dto.DepartmentInDTO;
import com.store.OnlineShop.service.dto.DiscountInDTO;
import com.store.OnlineShop.service.dto.ProductInDTO;

public class Validations {
	private Pattern pattern = Pattern.compile("[^\\w\\s$]");
//	private static final Logger logger = LogManager.getLogger(Validations.class);
	
	public boolean validateString(String string) {
		if(string.isEmpty())return false;
		if(pattern.matcher(string).find())return false;
		return true;
	}
	
	public boolean validateNumber(Integer number) {
		String chain = String.valueOf(number);
		if(number==0 || chain.isBlank())return false;
		try{
			Integer.parseInt(chain);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean validateFloat(Float number) {
		String chain = String.valueOf(number);
		if(number==0 || chain.isBlank())return false;
		try{
			Float.parseFloat(chain);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean validateDate(LocalDate date) {
		String strDate = String.valueOf(date);
		if(strDate.trim().equals(""))return false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.parse(strDate);
			return true;
		}catch(ParseException e) {
			return false;
		}
	}
	
	public boolean validateSizeType(String word) {
		if(word.length()>1 || word.length()<1)return false;
		return true;
	}
	
	public boolean validateSizeAmount(String word) {
		if(word.length()>5 || word.length()<1)return false;
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
	
	public void validateDiscount(DiscountInDTO discount) {
		if(!validateNumber(discount.getProd_id()))throw new ShopExceptions("Invalid product id",HttpStatus.NOT_ACCEPTABLE);
		if(!validateSizeType(discount.getDiscount_type()))throw new ShopExceptions("Invalid discount type",HttpStatus.NOT_ACCEPTABLE);
		if(!validateString(discount.getDiscount_type()))throw new ShopExceptions("Invalid discount type",HttpStatus.NOT_ACCEPTABLE);
		if(!validateSizeAmount(discount.getDiscount_amount()))throw new ShopExceptions("Invalid discount amount",HttpStatus.NOT_ACCEPTABLE);
		if(!validateString(discount.getDiscount_amount()))throw new ShopExceptions("Invalid discount type",HttpStatus.NOT_ACCEPTABLE);
		if(!validateDate(discount.getDate_begin()))throw new ShopExceptions("Invalid begin date",HttpStatus.NOT_ACCEPTABLE);
		if(!validateDate(discount.getDate_expire()))throw new ShopExceptions("Invalid expire date",HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	
	
	
	
	
	
	
}
