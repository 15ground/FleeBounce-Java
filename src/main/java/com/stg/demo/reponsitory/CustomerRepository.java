package com.stg.demo.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stg.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//	Search theo category
	@Query(value = "select * from customer where customer.phone_number = :phoneNumber and customer.password = :password", nativeQuery = true)
	Customer login(@Param(value = "phoneNumber") String phoneNumber, @Param(value = "password") String password);
}
