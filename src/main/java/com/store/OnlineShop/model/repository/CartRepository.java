package com.store.OnlineShop.model.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.store.OnlineShop.model.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer>{

//	@Modifying
//	@Query(value = "call addToCart(:ticket,:prod_id,:quantity,:subtotal)", nativeQuery = true)
//	public Cart addToCart(@Param("ticket") int ticket,@Param("prod_id") int prod_id,@Param("quantity") int quantity,@Param("subtotal") float subtotal);
}
