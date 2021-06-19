package com.stg.demo.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stg.demo.model.Products;

public interface ProductsResponsitory extends JpaRepository<Products, Integer> {
	Page<Products> findByNameContainingIgnoreCase(String name, Pageable pager);

//	Search theo category
	@Query(value = "select * from products as a, categories as b where a.category_id = b.id and b.id = :category_id", nativeQuery = true)
	Page<Products> searchProductsByCategoryID(@Param(value = "category_id") int category_id, Pageable pager);
	
	@Query(value = "SELECT * FROM products WHERE (CASE WHEN :categoryID > 0 THEN category_id = :categoryID ELSE true END) and lower(name) LIKE lower(concat('%',:key, '%')) ", nativeQuery = true)
	Page<Products> searchProductsPagin(	@Param(value = "key") String key,
										@Param(value = "categoryID") int categoryID, 
										Pageable pager);
	
}
