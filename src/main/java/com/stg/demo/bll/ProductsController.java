package com.stg.demo.bll;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stg.demo.model.Category;
import com.stg.demo.model.Products;
import com.stg.demo.reponsitory.CategoriesReponsitory;
import com.stg.demo.reponsitory.ProductsResponsitory;

@Controller
@RequestMapping("products")
public class ProductsController {
	@Autowired
	CategoriesReponsitory categoriesResponsitory;

	@ModelAttribute("categories")
	public List<Category> getCategories() {
		List<Category> categories = categoriesResponsitory.findAll();
		return categories;
	}

	@GetMapping(path = "insert")
	public String insert(Model model) {
		Products products = new Products();
		model.addAttribute("products", products);
		return "products/insert";
	}

	@Autowired
	ProductsResponsitory productsResponsitory;

	@PostMapping(path = "insert")
	public String insertComplete(@Valid @ModelAttribute("products") Products products, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "products/insert";
		}
		productsResponsitory.save(products);
		return "redirect:list";
	}

	/*
	 * @GetMapping("see") public String list(Model model) { List<Products> products
	 * = productsResponsitory.findAll(); model.addAttribute("products", products);
	 * return "products/list"; }
	 */

	@GetMapping("home")
	public String index(Model model) {
		List<Products> products = productsResponsitory.findAll();
		model.addAttribute("products", products);
		return "products/listproducts";
	}

	@GetMapping("sort")
	public String index(@RequestParam(name = "order",
			// cài đặt giá trị mặc định để tránh lỗi
			defaultValue = "id") String orderFeild, Model model) {
		List<Products> products = productsResponsitory.findAll(
				// Direction có 2 loại
				// ASC tăng dần
				// DESC là giảm dần
				Sort.by(Direction.ASC, orderFeild));
		model.addAttribute("products", products);
		return "products/listitems";
	}

	@GetMapping("edit")
	public String index(@RequestParam(name = "id") int cId, Model model) {
		Optional<Products> productsOption = productsResponsitory.findById(cId);
		if (productsOption.isEmpty())
			return "redirect:list";
		model.addAttribute("products", productsOption.get());
		return "products/edit";
	}

	@PostMapping("edit")
	public String index(@Valid @ModelAttribute("products") Products products, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "products/edit";
		}

		Optional<Products> productsOption = productsResponsitory.findById(products.getId());
		if (productsOption.isEmpty())
			return "redirect:list";

		Products productsOld = productsOption.get();
		productsOld.setName(products.getName());
		productsOld.setPrice(products.getPrice());
		productsOld.setImages(products.getImages());
		productsOld.setCategory(products.getCategory());
		productsOld.setPictures(products.getPictures());
		productsResponsitory.save(productsOld);

		return "redirect:list";
	}

	@GetMapping("delete")
	public String delete(@RequestParam(name = "id") int cId) {
		Optional<Products> productsOption = productsResponsitory.findById(cId);
		if (productsOption.isEmpty())
			return "redirect:list";
		productsResponsitory.delete(productsOption.get());
		return "redirect:list";
	}

	@GetMapping("details")
	public String details(@RequestParam(name = "id") int cId, Model model) {
		Optional<Products> productsOption = productsResponsitory.findById(cId);
		if (productsOption.isEmpty())
			return "redirect:list";
		model.addAttribute("proDetails", productsOption.get());
		return "prodetails";
	}

	// cài đặt tối đa 2 sản phẩm trên 1 trang
	private static final int MAX_ITEMS = 3;

//	@GetMapping("see")
//	public String pagination(@RequestParam(name = "pageIndex", defaultValue = "0") int pageIndex,
//			@RequestParam(name = "name", defaultValue = "") String name, Model model) {
//		// Tạo phân trang
//		Pageable pager = PageRequest.of(pageIndex, MAX_ITEMS);
//
//		// lấy sản phẩm
//		Page<Products> productPage = productsResponsitory.findByNameContainingIgnoreCase(name, pager);
//
//		model.addAttribute("products", productPage.getContent());
//		// truyền vào số lượng page tối đa
//		model.addAttribute("maxPage", productPage.getTotalPages());
//		model.addAttribute("search", name);
//		return "products/listitems";
//	}

	@GetMapping(value = { "list" })
	public String search(@RequestParam(name = "pageIndex", defaultValue = "0") int pageIndex,
			// thêm từ khóa tìm kiếm
			@RequestParam(name = "search", defaultValue = "") String search, Model model) {
		Pageable pager = PageRequest.of(pageIndex, MAX_ITEMS);
		// lấy sản phẩm
		Page<Products> productPage = productsResponsitory.findByNameContainingIgnoreCase(search, pager);
		System.out.println("Name: " + search);
		model.addAttribute("products", productPage.getContent());
		model.addAttribute("maxPage", productPage.getTotalPages());
		model.addAttribute("search", search);
		return "products/listitems";
	}
}
