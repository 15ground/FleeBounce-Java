package com.stg.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.demo.model.Cart;
import com.stg.demo.model.Products;
import com.stg.demo.reponsitory.ProductsResponsitory;

@Service
public class CategoryServiceImpl implements CartService {
	@Autowired
	private Cart cart;

	public Cart getGioHang() {
		return cart;
	}

	@Autowired
	ProductsResponsitory productsRepository;

	@Override
	public void themSanPham(int productId) {
		Products products = productsRepository.findById(productId).get();
		if (cart.getCartDetails().containsKey(products)) {
			int count = cart.getCartDetails().get(products);
			cart.getCartDetails().replace(products, count + 1);
		} else {
			cart.getCartDetails().put(products, 1);
		}
	}

	@Override
	public void truSanPham(int productId) {
		Products product = productsRepository.findById(productId).get();
		if (cart.getCartDetails().containsKey(product)) {
			int count = cart.getCartDetails().get(product);
			cart.getCartDetails().replace(product, count - 1);
		}
	}

	@Override
	public void xoaSanPham(int productId) {
		Products product = productsRepository.findById(productId).get();
		if (cart.getCartDetails().containsKey(product)) {
			cart.getCartDetails().remove(product);
		}
	}
}