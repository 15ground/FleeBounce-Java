package com.stg.demo.service;

import com.stg.demo.model.Customer;

public interface CustomerService {
	
	Customer getCustomer();
	boolean Register(Customer customer);
	boolean Logout(int customer_id);
	boolean Login(String phoneNumber, String password);
	boolean isCustomerLogin();
	void ConfirmLogout();
	boolean isAdmin();
}
