package com.store.OnlineShop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.OnlineShop.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	@Modifying
	@Query(value = "call updateProduct(:id,:nname,:ncost,:nprice,:ndescription,:ndep_id)",nativeQuery = true)
	public void updateProduct(@Param("id") int id,@Param("nname") String nname,@Param("ncost") float ncost,@Param("nprice") float nprice,
			@Param("ndescription") String ndescription,@Param("ndep_id") int ndep_id);

	@Query(value = "SELECT * FROM product where name=:name",nativeQuery = true)
	public Product testGetProduct(@Param("name") String name);
}
