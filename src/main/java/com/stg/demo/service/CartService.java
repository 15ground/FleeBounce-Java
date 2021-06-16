package com.stg.demo.service;

import com.stg.demo.model.Cart;

public interface CartService {
	public Cart getGioHang();

	public void themSanPham(int productId);
	public void truSanPham(int productId);
	public void xoaSanPham(int productId);
}
