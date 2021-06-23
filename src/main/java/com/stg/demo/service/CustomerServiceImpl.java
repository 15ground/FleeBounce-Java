package com.stg.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.demo.model.Customer;
import com.stg.demo.reponsitory.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerSession customerSession;

	@Override
	public Customer getCustomer() {
		return customerSession.getCustomer();
	}
	@Override
	public boolean Register(Customer customer) {
		Customer customerSave = customerRepository.save(customer);

		if (customerSave != null) {
			customerSession.setCustomer(customerSave);
			return true;
		}
		return false;
	}

	@Override
	public boolean Logout(int customer_id) {
		customerSession.setCustomer(null);
		return true;
	}

	@Override
	public boolean Login(String phoneNumber, String password) {
		Customer customer = customerRepository.login(phoneNumber, password);
		if(customer != null) {
			customerSession.setCustomer(customer);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isCustomerLogin() {
		return customerSession.getCustomer() != null;
	}
	
}
