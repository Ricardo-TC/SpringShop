package com.store.OnlineShop.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.store.OnlineShop.model.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{

//	@Procedure(procedureName = "getCartByTicket")
	@Query(value = "call getCartByTicket(:ticket)", nativeQuery = true)
	public List<Cart> getCartByTicket(@Param("ticket") Integer ticket);
	
	@Modifying
	@Query(value = "call updateProductFromCart(:quantity,:id,:ticket,:subtotal)", nativeQuery = true)
	public void updateProductFromCart(@Param("quantity") Integer quantity,@Param("id") Integer id,@Param("ticket") Integer ticket,@Param("subtotal") Float subtotal);
}