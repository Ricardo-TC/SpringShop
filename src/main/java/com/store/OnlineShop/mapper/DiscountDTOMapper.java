package com.store.OnlineShop.mapper;

import org.springframework.stereotype.Component;

import com.store.OnlineShop.model.entity.Discount;
import com.store.OnlineShop.service.dto.DiscountInDTO;

@Component
public class DiscountDTOMapper implements IMapper<DiscountInDTO,Discount> {

	@Override
	public Discount map(DiscountInDTO in) {
		Discount discount = new Discount();
		discount.setProd_id(in.getProd_id());
		discount.setDiscount_type(in.getDiscount_type());
		discount.setDiscount_amount(in.getDiscount_amount());
		discount.setDate_begin(in.getDate_begin());
		discount.setDate_expire(in.getDate_expire());
		return discount;
	}

}
