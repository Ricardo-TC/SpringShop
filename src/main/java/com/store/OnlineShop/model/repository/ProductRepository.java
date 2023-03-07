package com.store.OnlineShop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.OnlineShop.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

}
