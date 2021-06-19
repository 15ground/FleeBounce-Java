package com.stg.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
