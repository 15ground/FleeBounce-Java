package com.stg.demo.bll;

import java.util.List;

import javax.validation.Valid;

import com.stg.demo.model.Products;
import com.stg.demo.model.SearchForm;
import com.stg.demo.reponsitory.ProductsResponsitory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsRestController {
    @Autowired
    ProductsResponsitory productsResponsitory;
    @GetMapping("/api/products")
    public List<Products> list(){
        return productsResponsitory.findAll();
    }
    @GetMapping("/api/products/{id}")
	public Products getById(@PathVariable("id") int id) {
		return productsResponsitory.findById(id).orElse(null);
	}
	
	@PostMapping("/api/products")
	public Products insert(@Valid @RequestBody Products products) {
		return productsResponsitory.save(products);
	}
	
	@PutMapping("/api/products")
	public Products update(@Valid @RequestBody Products products) {
		return productsResponsitory.save(products);
	}

	@DeleteMapping("/api/products/{id}")
	public void delete(@PathVariable("id") int id) {
		productsResponsitory.deleteById(id);
	}

	private static final int LIMIT_ITEMS = 3;
	@PostMapping("api/products/search")
	public Page<Products> search(@RequestBody SearchForm sf){
		Pageable paging = PageRequest.of(sf.getPage(),
				LIMIT_ITEMS,
				// nếu đúng thì thứ tự tăng đần ngược lại giảm dần
				sf.isIndex() ? Direction.ASC: Direction.DESC,
				// xếp theo trường nào ví dụ id, name, price
				sf.getSortBy());
		
		// lấy sản phẩm
		Page<Products> productPage = productsResponsitory
				.findByNameContainingIgnoreCase(sf.getName(), paging);
		return productPage;
	}
}
