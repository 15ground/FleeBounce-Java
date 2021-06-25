package com.stg.demo.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	Page<Order> findByNameContainingIgnoreCase(String name, Pageable pager);
}
