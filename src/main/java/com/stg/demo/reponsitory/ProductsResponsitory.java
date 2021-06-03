package com.stg.demo.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.demo.model.Products;

public interface ProductsResponsitory extends JpaRepository<Products, Integer> {
	Page<Products> findByNameContaining(String name, Pageable pager);
}
