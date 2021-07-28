package com.stg.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.stg.demo.service.CartService;

@Component
public class CartInterceptor implements HandlerInterceptor {
	@Autowired
	private CartService cartService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// nếu giỏ hàng trống thì chuyển về trang chủ
		if (cartService.getGioHang().getCartDetails().size() == 0) {
			response.sendRedirect("/empty");
			return false;
		}
		return true;
	}
}
