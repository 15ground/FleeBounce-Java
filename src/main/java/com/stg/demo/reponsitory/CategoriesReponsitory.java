package com.stg.demo.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.demo.model.Category;

public interface CategoriesReponsitory extends JpaRepository<Category, Integer> {
	Page<Category> findByNameContainingIgnoreCase(String name, Pageable pager);
}
