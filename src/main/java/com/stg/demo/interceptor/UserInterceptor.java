package com.stg.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.stg.demo.service.CustomerService;

@Component
public class UserInterceptor implements HandlerInterceptor {
	@Autowired
	CustomerService customerService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// nếu giỏ hàng trống thì chuyển về trang chủ
		if (!customerService.isCustomerLogin() || !customerService.isAdmin()) {
			response.sendRedirect("/home");
			return false;
		}
		return true;
	}
}
