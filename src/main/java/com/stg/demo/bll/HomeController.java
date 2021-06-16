package com.stg.demo.bll;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stg.demo.bll.HomeController;
import com.stg.demo.model.Categories;
import com.stg.demo.model.Products;
import com.stg.demo.service.CartService;
import com.stg.demo.service.MailService;

@Controller
public class HomeController {
	public static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	public static Date today = new Date();

	public static final List<Categories> CATEGORIES = new ArrayList<Categories>();
	static {
		CATEGORIES.add(new Categories(0, "Áo thể thao"));
		CATEGORIES.add(new Categories(1, "Quần thể thao"));
		CATEGORIES.add(new Categories(2, "Giày thể thao"));
	}
	public static final List<Products> PRODUCTS = new ArrayList<Products>();
	static {

		PRODUCTS.add(new Products(0, "Áo puma 1",
				"https://fuvy.vn/www/uploads/images/%C3%A1o-puma-amplified-men-big-logo.jpg", today, 900000,
				"Áo puma thiết kế đẹp, thoải mái khi vận động thể thao..."));
		PRODUCTS.add(new Products(1, "Áo puma 2",
				"https://fuvy.vn/www/uploads/images/%C3%A1o-puma-amplified-men-big-logo.jpg", today, 900000,
				"Áo puma thiết kế đẹp, thoải mái khi vận động thể thao..."));
		PRODUCTS.add(new Products(2, "Áo puma 3",
				"https://fuvy.vn/www/uploads/images/%C3%A1o-puma-amplified-men-big-logo.jpg", today, 900000,
				"Áo puma thiết kế đẹp, thoải mái khi vận động thể thao..."));
		PRODUCTS.add(new Products(3, "Áo puma 4",
				"https://fuvy.vn/www/uploads/images/%C3%A1o-puma-amplified-men-big-logo.jpg", today, 900000,
				"Áo puma thiết kế đẹp, thoải mái khi vận động thể thao..."));
		PRODUCTS.add(new Products(4, "Áo puma 5",
				"https://fuvy.vn/www/uploads/images/%C3%A1o-puma-amplified-men-big-logo.jpg", today, 900000,
				"Áo puma thiết kế đẹp, thoải mái khi vận động thể thao..."));
		PRODUCTS.add(new Products(6, "Áo puma 6",
				"https://fuvy.vn/www/uploads/images/%C3%A1o-puma-amplified-men-big-logo.jpg", today, 900000,
				"Áo puma thiết kế đẹp, thoải mái khi vận động thể thao..."));
		PRODUCTS.add(new Products(7, "Áo puma 7",
				"https://fuvy.vn/www/uploads/images/%C3%A1o-puma-amplified-men-big-logo.jpg", today, 900000,
				"Áo puma thiết kế đẹp, thoải mái khi vận động thể thao..."));
		PRODUCTS.add(new Products(8, "Áo puma 8",
				"https://fuvy.vn/www/uploads/images/%C3%A1o-puma-amplified-men-big-logo.jpg", today, 900000,
				"Áo puma thiết kế đẹp, thoải mái khi vận động thể thao..."));
	}

	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("categories", CATEGORIES);
		model.addAttribute("products", PRODUCTS);
		return "home";
	}

	@GetMapping("dashboard")
	public String dashboard() {
		return "dashboard";
	}

	@GetMapping("test")
	public String test() {
		return "index";
	}

	@Autowired
	CartService cartService;

	@GetMapping("cart")
	public void gioHang(Model model) {
		model.addAttribute("gioHang", cartService.getGioHang());
	}

	@GetMapping("them-vao-gio/{idSanPham}")
	public String addGioHang(@PathVariable("idSanPham") int idSanPham) {
		cartService.themSanPham(idSanPham);
		return "redirect:/cart";
	}

	@GetMapping("tru-san-pham/{idSanPham}")
	public String truSanPham(@PathVariable("idSanPham") int idSanPham) {
		cartService.truSanPham(idSanPham);
		return "redirect:/cart";
	}

	@GetMapping("xoa-san-pham/{idSanPham}")
	public String xoaSanPham(@PathVariable("idSanPham") int idSanPham) {
		cartService.xoaSanPham(idSanPham);
		return "redirect:/cart";
	}

	@GetMapping("hoan-thanh-dat-hang")
	public String hoanthanhgio() {
		return "checkout";
	}

	@Autowired
	MailService mailService;

	@PostMapping("hoan-thanh")
	public String hoanthanhgio(@RequestParam("email") String email) {
		mailService.pushMail(email);
		return "redirect:/test";
	}
}
