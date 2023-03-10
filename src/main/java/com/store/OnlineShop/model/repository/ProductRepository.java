package com.store.OnlineShop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.OnlineShop.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	@Modifying
	@Query(value = "call updateProduct(:id,:nname,:ncost,:nprice,:ndiscount_type,:ndiscount_amount,:ndescription,:ndep_id)",nativeQuery = true)
	public void updateProduct(@Param("id") int id,@Param("nname") String nname,@Param("ncost") float ncost,@Param("nprice") float nprice,
			@Param("ndiscount_type") String ndiscount_type,@Param("ndiscount_amount") String ndiscount_amount,@Param("ndescription") String ndescription,
			@Param("ndep_id") int ndep_id);

	@Modifying
	@Query(value = "call updateProductDiscount(:id,:dis_type,:dis_amount)", nativeQuery = true)
	public void updateProductDiscount(@Param("id") int id,@Param("dis_type") String dis_type,@Param("dis_amount") String dis_amount);
}
