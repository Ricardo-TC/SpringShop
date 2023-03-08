package com.store.OnlineShop.mapper;

import org.springframework.stereotype.Component;

import com.store.OnlineShop.model.entity.Product;
import com.store.OnlineShop.service.dto.ProductDiscountInDTO;

@Component
public class ProductDiscountDTOMapper implements IMapper<ProductDiscountInDTO,Product> {

	@Override
	public Product map(ProductDiscountInDTO in) {
		Product product = new Product();
		product.setDiscount_type(in.getDiscount_type());
		product.setDiscount_amount(in.getDiscount_amount());
		return product;
	}

}
