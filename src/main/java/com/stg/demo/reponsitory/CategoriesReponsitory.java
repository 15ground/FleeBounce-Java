package com.stg.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stg.demo.model.Category;

public interface CategoriesReponsitory extends JpaRepository<Category, Integer> {
	@Query(value = "select count(p.id) " + "from products p inner join categories c "
			+ "on p.category_id = c.id " + "where c.name like concat('%',:name,'%')", nativeQuery = true)
	int countByCategoryContainName(@Param("name") String name);

	@Query(value = "select count(p.id) " + "from products p inner join categories c "
			+ "on p.category.categoryID = c.categoryID " + "where c.name like %:name%")
	int countByCategoryContainNameJPA(@Param("name") String name);
}
