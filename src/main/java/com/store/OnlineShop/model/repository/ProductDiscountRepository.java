package com.store.OnlineShop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.OnlineShop.model.entity.Product;

public interface ProductDiscountRepository extends JpaRepository<Product,Integer>{
	
	@Modifying
	@Query(value = "call updateProductDiscount(:id,:dis_type,:dis_amount)", nativeQuery = true)
	public void updateProductDiscount(@Param("id") int id,@Param("dis_type") String dis_type,@Param("dis_amount") String dis_amount);

}
