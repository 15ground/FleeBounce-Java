package com.stg.demo.bll;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.stg.demo.model.SearchForm;
import com.stg.demo.reponsitory.CategoriesReponsitory;

@Controller
@RequestMapping("category")
public class CategoriesController {

	@Autowired
	CategoriesReponsitory categoryResponsitory;

	@GetMapping(path = "insert")
	public String insert(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "category/insert";
	}

	// Cài đặt tối đa 5 sản phẩm trên 1 trang Dashboard
	private static final int MAX_ITEMS = 5;

	// Danh sách sản phẩm trong Dashboard
	@GetMapping("list")
	public String list(@ModelAttribute(name = "searchForm") SearchForm sf, Model model) {

		Pageable pagin = PageRequest.of(sf.getPage(), MAX_ITEMS, sf.isIndex() ? Direction.ASC : Direction.DESC,
				sf.getSortBy());
		// lấy sản phẩm
		Page<Category> categoryPage = categoryResponsitory.findByNameContainingIgnoreCase(sf.getName(), pagin);
		model.addAttribute("category", categoryPage.getContent());
		model.addAttribute("maxPage", categoryPage.getTotalPages());

		return "category/listitems";
	}

	@PostMapping(path = "insert")
	public String insertComplete(@Valid @ModelAttribute("category") Category category, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "category/insert";
		}
		categoryResponsitory.save(category);
		return "redirect:list";
	}

	@GetMapping("edit")
	public String index(@RequestParam(name = "id") int cId, Model model) {
		Optional<Category> categoryOption = categoryResponsitory.findById(cId);
		if (categoryOption.isEmpty())
			return "redirect:list";
		model.addAttribute("category", categoryOption.get());
		return "category/edit";
	}

	@PostMapping("edit")
	public String index(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "category/edit";
		}

		Optional<Category> categoryOption = categoryResponsitory.findById(category.getId());
		if (categoryOption.isEmpty())
			return "redirect:list";

		Category categoryOld = categoryOption.get();
		categoryOld.setName(category.getName());
		categoryResponsitory.save(categoryOld);

		return "redirect:list";
	}

	@GetMapping("delete")
	public String delete(@RequestParam(name = "id") int cId) {
		Optional<Category> categoryOption = categoryResponsitory.findById(cId);
		if (categoryOption.isEmpty())
			return "redirect:list";
		categoryResponsitory.delete(categoryOption.get());
		return "redirect:list";
	}

}
