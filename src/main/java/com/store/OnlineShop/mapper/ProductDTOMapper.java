package com.store.OnlineShop.mapper;

import org.springframework.stereotype.Component;

import com.store.OnlineShop.model.entity.Product;
import com.store.OnlineShop.service.dto.ProductInDTO;

@Component
public class ProductDTOMapper implements IMapper<ProductInDTO,Product>{

	@Override
	public Product map(ProductInDTO in) {
		Product product = new Product();
		product.setName(in.getName());
		product.setCost(in.getCost());
		product.setPrice(in.getPrice());
		product.setDescription(in.getDescription());
		product.setDep_id(in.getDep_id());
		return product;
	}

}
