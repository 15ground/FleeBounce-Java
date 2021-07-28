package com.stg.demo.bll;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.stg.demo.model.Category;
import com.stg.demo.model.Customer;
import com.stg.demo.model.Login;
import com.stg.demo.model.Order;
import com.stg.demo.model.OrderItems;
import com.stg.demo.model.Products;
import com.stg.demo.reponsitory.CategoriesReponsitory;
import com.stg.demo.reponsitory.OrderItemsRepository;
import com.stg.demo.reponsitory.OrderRepository;
import com.stg.demo.reponsitory.ProductsResponsitory;
import com.stg.demo.service.CartService;
import com.stg.demo.service.CustomerService;
import com.stg.demo.service.MailService;

@Controller
@RequestMapping("")
public class HomeController {

	@Autowired
	MailService mailService;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderItemsRepository orderItemsRepository;

	@Autowired
	CartService cartService;

	@Autowired
	CustomerService customerService;

	// @RequestMapping("/")
	// public String home(Model model) {

	// 	model.addAttribute("message", "Hello");

	// 	return "index";
	// }

	@Autowired
	CategoriesReponsitory categoriesResponsitory;
	@Autowired
	ProductsResponsitory productsResponsitory;
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		List<Category> categories = categoriesResponsitory.findAll();
		return categories;
	}

	// Cài đặt tối đa 8 sản phẩm trên 1 trang Home
	private static final int HOME_ITEMS = 8;

	@GetMapping(value = { "/", "search" })
	public String home(@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "categoryID", defaultValue = "0") int categoryID,
			@RequestParam(value = "page", defaultValue = "0") int page, Model model) {

			Pageable pagin = PageRequest.of(page, HOME_ITEMS);
			// // lấy sản phẩm
			Page<Products> productPage = productsResponsitory.searchProductsPagin(key, categoryID, pagin);

		 

		// // data
		model.addAttribute("categories", getCategories());
		model.addAttribute("products", productPage.getContent());
		model.addAttribute("maxPage", productPage.getTotalPages());

		model.addAttribute("page", page);

		// // search
		model.addAttribute("categoryID", categoryID);
		model.addAttribute("key", key);
		

		return "index";
	}

	@GetMapping("error")
	public String error() {
		return "error";
	}

	@GetMapping("empty")
	public String empty() {
		return "empty";
	}

	@GetMapping("dashboard")
	public String dashboard() {
		return "dashboard";
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

	// dang nhap
	@GetMapping("login")
	public String login(@RequestParam(value = "cartStatus", defaultValue = "0") int cartStatus, Model model) {
		model.addAttribute("cartStatus", cartStatus);
		model.addAttribute("login", new Login());
		return "login";
	}

	@PostMapping("login")
	public String loginPost(@RequestParam(value = "cartStatus", defaultValue = "0") int cartStatus,
			@Valid @ModelAttribute("login") Login login, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (customerService.Login(login.getPhoneNumber(), login.getPassWord())) {
			model.addAttribute("cart", cartService.getGioHang());
			model.addAttribute("order", new Order());
			model.addAttribute("name", customerService.getCustomer().getName());
			model.addAttribute("phoneNumber", customerService.getCustomer().getPhoneNumber());
			model.addAttribute("address", customerService.getCustomer().getAddress());
			session.setAttribute("username", customerService.getCustomer().getName());
			session.setAttribute("currentUser", customerService.getCustomer());
			return cartStatus == 1 ? "checkout" : "redirect:/products";
		} else {
			model.addAttribute("cartStatus", cartStatus);
			model.addAttribute("login", login);
			session.setAttribute("message", "Tài khoản hoặc mật khẩu không chính xác!");
			return "login";
		}

	}

	// dang kí
	@PostMapping("register")
	public String register(@RequestParam(value = "cartStatus", defaultValue = "0") int cartStatus,
			@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model,
			HttpServletRequest request) {
		// valid data
		if (result.hasErrors()) {
			return "register";
		}
		HttpSession session = request.getSession();
		// check dang ki
		if (customerService.Register(customer)) {
			model.addAttribute("cart", cartService.getGioHang());
			model.addAttribute("order", new Order());
			model.addAttribute("name", customerService.getCustomer().getName());
			model.addAttribute("phoneNumber", customerService.getCustomer().getPhoneNumber());
			model.addAttribute("address", customerService.getCustomer().getAddress());
			session.setAttribute("username", customerService.getCustomer().getName());
			session.setAttribute("currentUser", customerService.getCustomer());
			return cartStatus == 1 ? "checkout" : "redirect:/products";

		} else {
			model.addAttribute("cartStatus", cartStatus);
			return "register";
		}

	}

	@GetMapping("register")
	public String registerGet(@RequestParam(value = "cartStatus", defaultValue = "0") int cartStatus, Model model) {
		model.addAttribute("cartStatus", cartStatus);
		model.addAttribute("customer", new Customer());
		return "register";
	}

	@PostMapping("logout")
	public String logOut(@RequestParam(value = "cartStatus", defaultValue = "0") int cartStatus, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (customerService.Logout(customerService.getCustomer().getId())) {
			session.removeAttribute("username");
			session.removeAttribute("currentUser");
			// lam moi lai gio hang
			cartService.getGioHang().getCartDetails().clear();
		}
		return "redirect:/products";
	}
	/// XU LY GIO HANG

	// trang gio hang
	@GetMapping("your-cart")
	public void gioHang(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("cart", cartService.getGioHang());
	}

	// kiem tra dang nhap truoc khi tiep tuc thanh toan
	@GetMapping("check-out")
	public String checkout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (customerService.isCustomerLogin()) {
			session.setAttribute("cart", cartService.getGioHang());
			model.addAttribute("order", new Order());
			model.addAttribute("name", customerService.getCustomer().getName());
			model.addAttribute("phoneNumber", customerService.getCustomer().getPhoneNumber());
			model.addAttribute("address", customerService.getCustomer().getAddress());
			model.addAttribute("cartStatus", 0);
			return "checkout";
		} else {
			model.addAttribute("cartStatus", 1);
			model.addAttribute("login", new Login());
			return "login";
		}
	}

	// trang xac nhan don hang
	@RequestMapping("check-out")
	public String hoanthanhgio(@Valid @ModelAttribute("order") Order order, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "checkout";
		}
		// luu order
		order.setCustomer(customerService.getCustomer());
		Order orderSave = orderRepository.save(order);

		// luu orderItems
		Map<Products, Integer> listItems = cartService.getGioHang().getCartDetails();

		for (Products product : listItems.keySet()) {

			OrderItems orderItem = new OrderItems();
			int amount = listItems.get(product);
			orderItem.setProducts(product);
			orderItem.setOrder(orderSave);
			orderItem.setAmount(amount);
			orderItem.setName(product.getName());
			orderItem.setPrice(product.getPrice());
			orderItem.setTotal((product.getPrice()) * amount);
			orderItemsRepository.save(orderItem);
		}

		// lam moi lai gio hang
		cartService.getGioHang().getCartDetails().clear();

		// gui mail de duoi cung
		mailService.sendMailWithOrderId(order.getId());

		return "redirect:/products";
	}
}
