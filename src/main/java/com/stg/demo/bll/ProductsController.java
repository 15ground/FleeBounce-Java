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
import com.stg.demo.model.SearchForm;
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

	// cài đặt tối đa 8 sản phẩm trên 1 trang
	private static final int HOME_ITEMS = 8;

	@GetMapping("")
	public String testlist(@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "categoryID", defaultValue = "0") int categoryID,
			@RequestParam(value = "page", defaultValue = "0") int page, Model model) {

		Pageable pagin = PageRequest.of(page, HOME_ITEMS);
		// lấy sản phẩm
		Page<Products> productPage = productsResponsitory.searchProductsPagin(key, categoryID, pagin);

		// data
		model.addAttribute("categories", getCategories());
		model.addAttribute("products", productPage.getContent());
		// pagin
		model.addAttribute("maxPage", productPage.getTotalPages());
		model.addAttribute("page", page);

		// search
		model.addAttribute("categoryID", categoryID);

		return "products/listproducts";
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
		productsOld.setDescription(products.getDescription());
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

	// cài đặt tối đa 5 sản phẩm trên 1 trang
	private static final int MAX_ITEMS = 5;

	/*
	 * CÁCH CŨ
	 * 
	 * @GetMapping(value = { "list" }) public String search(@RequestParam(name =
	 * "pageIndex", defaultValue = "0") int pageIndex, // thêm từ khóa tìm kiếm
	 * 
	 * @RequestParam(name = "search", defaultValue = "") String search, Model model)
	 * { Pageable pager = PageRequest.of(pageIndex, MAX_ITEMS); // lấy sản phẩm
	 * Page<Products> productPage =
	 * productsResponsitory.findByNameContainingIgnoreCase(search, pager);
	 * System.out.println("Name: " + search); model.addAttribute("products",
	 * productPage.getContent()); model.addAttribute("maxPage",
	 * productPage.getTotalPages()); model.addAttribute("search", search); return
	 * "products/listitems"; }
	 */

	@GetMapping("list")
	public String list(@ModelAttribute(name = "searchForm") SearchForm sf, Model model) {

		Pageable pagin = PageRequest.of(sf.getPage(), MAX_ITEMS, sf.isIndex() ? Direction.ASC : Direction.DESC,
				sf.getSortBy());
		// lấy sản phẩm
		Page<Products> productPage = productsResponsitory.findByNameContainingIgnoreCase(sf.getName(), pagin);
		model.addAttribute("products", productPage.getContent());
		model.addAttribute("maxPage", productPage.getTotalPages());

		return "products/listitems";
	}
}
