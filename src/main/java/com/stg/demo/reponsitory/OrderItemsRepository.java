package com.stg.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.demo.model.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {

}
