package com.store.OnlineShop.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.store.OnlineShop.model.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer>{

//	@Procedure(procedureName = "getCartByTicket")
	@Query(value = "call getCartByTicket(:ticket)", nativeQuery = true)
	public List<Cart> getCartByTicket(@Param("ticket") Integer ticket);
}
