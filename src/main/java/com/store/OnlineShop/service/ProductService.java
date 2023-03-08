package com.store.OnlineShop.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.store.OnlineShop.exceptions.ShopExceptions;
import com.store.OnlineShop.mapper.ProductDTOMapper;
import com.store.OnlineShop.model.entity.Product;
import com.store.OnlineShop.model.repository.ProductDiscountRepository;
import com.store.OnlineShop.model.repository.ProductRepository;
import com.store.OnlineShop.service.dto.ProductDiscountInDTO;
import com.store.OnlineShop.service.dto.ProductInDTO;
import com.store.OnlineShop.util.Validations;

@Service
public class ProductService {
	
	private final ProductRepository repository;
	private final ProductDiscountRepository discountRepository;
	private final ProductDTOMapper mapper;
	private final Validations validations = new Validations();
	public ProductService(ProductRepository repository, ProductDTOMapper mapper,ProductDiscountRepository discountRepository) {
		super();
		this.repository = repository;
		this.mapper = mapper;
		this.discountRepository = discountRepository;
	}
	
	public Product createProduct(ProductInDTO productInDTO) {
		validations.validateProduct(productInDTO);
		Product product = mapper.map(productInDTO);
		return this.repository.save(product);
	}
	
	public List<Product> findAll(){
		return this.repository.findAll();
	}
	
	public Product findById(int id){
		Optional<Product> opt = repository.findById(id);
		if(opt.isEmpty())throw new ShopExceptions("Data not found",HttpStatus.NOT_FOUND);
		return this.repository.findById(id).get();
	}
	
	@Transactional
	public void updateProduct(int id, ProductInDTO productInDTO) {
		validations.validateProduct(productInDTO);
		if(repository.findById(id).isEmpty())throw new ShopExceptions("Data not found",HttpStatus.NOT_FOUND);
		this.repository.updateProduct(id, productInDTO.getName(), productInDTO.getCost(), productInDTO.getPrice(), productInDTO.getDiscount_type(), productInDTO.getDiscount_amount(), productInDTO.getDescription(), productInDTO.getDep_id());
	}
	
	@Transactional
	public void updateProductDiscount(int id, ProductDiscountInDTO producDiscountInDTO) {
		if(repository.findById(id).isEmpty())throw new ShopExceptions("Data not found",HttpStatus.NOT_FOUND);
		this.discountRepository.updateProductDiscount(id, producDiscountInDTO.getDiscount_type(),producDiscountInDTO.getDiscount_amount());
	}
	
	@Transactional
	public void updateDiscount(int id, String disType, String disAmount) {
		this.repository.updateProductDiscount(id, disType, disAmount);
	}
	
	
	public void deleteById(int id) {
		if(repository.findById(id).isEmpty())throw new ShopExceptions("Data not found",HttpStatus.NOT_FOUND);
		this.repository.deleteById(id);
	}

}
