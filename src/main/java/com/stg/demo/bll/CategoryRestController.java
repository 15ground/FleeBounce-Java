package com.stg.demo.bll;

import java.util.List;

import com.stg.demo.model.Category;
import com.stg.demo.model.SearchForm;
import com.stg.demo.reponsitory.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort.Direction;

@RestController
public class CategoryRestController {
	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/api/category")
	public List<Category> list() {
		return categoryRepository.findAll();
	}

	@GetMapping("/api/category/{id}")
	public Category getById(@PathVariable("id") int id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@DeleteMapping("/api/category/{id}")
	public void delete(@PathVariable("id") int id) {
		 categoryRepository.deleteById(id);
	}

	@PostMapping("/api/category")
	public Category insert(@RequestBody Category category) {
		return categoryRepository.save(category);
	}

	@PutMapping("/api/category")
	public Category update(@RequestBody Category category) {
		return categoryRepository.save(category);
	}
	private static final int LIMIT_ITEMS = 3;
	@PostMapping("api/category/search")
	public Page<Category> search(@RequestBody SearchForm sf){
		Pageable paging = PageRequest.of(sf.getPage(),
				LIMIT_ITEMS,
				// nếu đúng thì thứ tự tăng đần ngược lại giảm dần
				sf.isIndex() ? Direction.ASC: Direction.DESC,
				// xếp theo trường nào ví dụ id, name, price
				sf.getSortBy());
		
		// lấy sản phẩm
		Page<Category> categoryPage = categoryRepository
				.findByNameContainingIgnoreCase(sf.getName(), paging);
		return categoryPage;
	}
}