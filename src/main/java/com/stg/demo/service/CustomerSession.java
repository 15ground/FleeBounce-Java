package com.stg.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.stg.demo.model.Customer;

@Component
@SessionScope
public class CustomerSession {
	Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
