package com.stg.demo.bll;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stg.demo.bll.HomeController;
import com.stg.demo.model.Customer;

import com.stg.demo.reponsitory.CustomerRepository;

import com.stg.demo.service.CartService;
import com.stg.demo.service.MailService;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home() {
		return "redirect:/products";
	}

	@GetMapping("dashboard")
	public String dashboard() {
		return "dashboard";
	}

	@Autowired
	CartService cartService;

	@GetMapping("your-cart")
	public void gioHang(Model model) {
		model.addAttribute("cart", cartService.getGioHang());
	}

	@GetMapping("them-vao-gio/{idSanPham}")
	public String addGioHang(@PathVariable("idSanPham") int idSanPham) {
		cartService.themSanPham(idSanPham);
		return "redirect:/your-cart";
	}

	@GetMapping("tru-san-pham/{idSanPham}")
	public String truSanPham(@PathVariable("idSanPham") int idSanPham) {
		cartService.truSanPham(idSanPham);
		return "redirect:/your-cart";
	}

	@GetMapping("xoa-san-pham/{idSanPham}")
	public String xoaSanPham(@PathVariable("idSanPham") int idSanPham) {
		cartService.xoaSanPham(idSanPham);
		return "redirect:/your-cart";
	}

	@GetMapping("check-out")
	public String hoanthanhgio(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "checkout";
	}

	@Autowired
	MailService mailService;
	@Autowired
	CustomerRepository customerResponsitory;

	@RequestMapping("check-out")
	public String hoanthanhgio(@RequestParam("email") String email,
			@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "checkout";
		}
		customerResponsitory.save(customer);
		mailService.pushMail(email);
		return "redirect:/products";
	}
}
