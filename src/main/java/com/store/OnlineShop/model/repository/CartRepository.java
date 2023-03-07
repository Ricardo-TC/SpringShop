package com.store.OnlineShop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.OnlineShop.model.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer>{

}
