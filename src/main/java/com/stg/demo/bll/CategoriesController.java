package com.stg.demo.bll;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stg.demo.model.Category;
import com.stg.demo.model.Products;
import com.stg.demo.reponsitory.CategoriesReponsitory;

@Controller
@RequestMapping("category")
public class CategoriesController {

	@GetMapping(path = "insert")
	public String insert(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "category/insert";
	}

	@Autowired
	CategoriesReponsitory categoryResponsitory;

	@PostMapping(path = "insert")
	public String insertComplete(@Valid @ModelAttribute("category") Category category, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "category/insert";
		}
		categoryResponsitory.save(category);
		return "redirect:list";
	}

	@GetMapping("list")
	public String index(Model model) {
		List<Category> categories = categoryResponsitory.findAll();
		model.addAttribute("categories", categories);
		return "category/list";
	}

	@GetMapping("edit")
	public String index(@RequestParam(name = "id") int cId, Model model) {
		Optional<Category> categoryOption = categoryResponsitory.findById(cId);
		if (categoryOption.isEmpty())
			return "redirect:list";
		model.addAttribute("category", categoryOption.get());
		return "category/insert";
	}

	@PostMapping("edit")
	public String index(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "category/insert";
		}

		Optional<Category> categoryOption = categoryResponsitory.findById(category.getCategoryID());
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
	@GetMapping("count")
	@ResponseBody
	public String countProductLikeCategoryName(@RequestParam(name = "name") String name) {
		int c = categoryResponsitory.countByCategoryContainNameJPA(name);
		return "" + c;
	}
}
