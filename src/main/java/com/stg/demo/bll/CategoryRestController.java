package com.stg.demo.bll;

import java.util.List;
import java.util.Optional;

import com.stg.demo.model.Category;
import com.stg.demo.reponsitory.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;

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
	@GetMapping("/api/categori")
    Page<Category> getCategories(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        return categoryRepository.findAll(
                PageRequest.of(
                        page.orElse(0),
                        5,
                        Sort.Direction.ASC, sortBy.orElse("name")
                )
        );
    }
}