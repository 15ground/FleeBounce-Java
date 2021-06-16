package com.stg.demo.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Cart {
	private Map<Products, Integer> cartDetails = new HashMap<Products, Integer>();

	public Map<Products, Integer> getCartDetails() {
		return cartDetails;
	}

	public void setChiTietGioHang(Map<Products, Integer> cartDetails) {
		this.cartDetails = cartDetails;
	}
}
