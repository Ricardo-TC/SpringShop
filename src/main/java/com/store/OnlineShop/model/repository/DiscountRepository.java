package com.store.OnlineShop.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.store.OnlineShop.model.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Integer>{
	
	@Procedure(procedureName = "updateProductDiscount")
	public void updateDiscount(@Param("disc_id") Integer disc_id, @Param("prod_id") Integer prod_id, @Param("discount_type") String discount_type,
			@Param("discount_amount") String discount_amount, @Param("date_begin") LocalDate date_begin, @Param("date_expire") LocalDate date_expire);

//	@Procedure(procedureName = "getDiscountByProduct")
//	public List<Discount> getDiscountByProduct(@Param("prod_id") Integer prod_id);
	
	@Query(value = "call getDiscountByProduct(:prod_id)", nativeQuery = true)
	public List<Discount> getDiscountByProduct(@Param("prod_id") Integer prod_id);
	
	@Query(value = "call getDiscountByDate(:prod_id,:datenow)" ,nativeQuery = true)
	public Discount getActiveDiscount(@Param("prod_id") int prod_id,@Param("datenow") LocalDate datenow);
}
