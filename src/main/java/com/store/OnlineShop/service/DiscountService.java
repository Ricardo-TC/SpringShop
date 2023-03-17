package com.store.OnlineShop.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.store.OnlineShop.exceptions.ShopExceptions;
import com.store.OnlineShop.mapper.DiscountDTOMapper;
import com.store.OnlineShop.model.entity.Discount;
import com.store.OnlineShop.model.repository.DiscountRepository;
import com.store.OnlineShop.service.dto.DiscountInDTO;
import com.store.OnlineShop.util.Validations;

@Service
public class DiscountService {

	private final DiscountRepository repository;
	private final DiscountDTOMapper mapper;
	private final Validations validations = new Validations();
	
	public DiscountService(DiscountRepository repository, DiscountDTOMapper mapper) {
		super();
		this.repository = repository;
		this.mapper = mapper;
	}


	public Discount setNewDiscount(DiscountInDTO discountInDTO) {
			validations.validateDiscount(discountInDTO);
			Discount discount = mapper.map(discountInDTO);
			return this.repository.save(discount);
	}
	
	public List<Discount> findAll(){
		return this.repository.findAll();
	}
	
	public Discount findById(int disc_id) {
		Optional<Discount> opt = this.repository.findById(disc_id);
		if(opt.isEmpty())throw new ShopExceptions("Not found",HttpStatus.NOT_FOUND);
		return opt.get();
	}
	
	public List<Discount> getDiscountByProduct(int prod_id){
		List<Discount> discountList = this.repository.getDiscountByProduct(prod_id);
		
		if(discountList.isEmpty())throw new ShopExceptions("Data not found",HttpStatus.NO_CONTENT);
		return discountList;
	}
	
	public Discount getActiveDiscount(int prod_id) {
		return this.repository.getActiveDiscount(prod_id, LocalDate.now());
	}
	
	@Transactional
	public void updateDiscount(int disc_id, DiscountInDTO discountInDTO) {
		validations.validateDiscount(discountInDTO);
		if(this.repository.findById(disc_id).isEmpty())throw new ShopExceptions("Not Found",HttpStatus.NOT_FOUND);
		this.repository.updateDiscount(disc_id, discountInDTO.getProd_id(), discountInDTO.getDiscount_type(), discountInDTO.getDiscount_amount(), 
				discountInDTO.getDate_begin(), discountInDTO.getDate_expire());
	}
	
	public void deleteById(int id) {
		if(repository.findById(id).isEmpty())throw new ShopExceptions("ID Not Found",HttpStatus.NOT_FOUND);
		this.repository.deleteById(id);
	}
}
