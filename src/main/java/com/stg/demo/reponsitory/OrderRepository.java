package com.stg.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
