package com.stg.demo.bll;

import java.util.List;

import com.stg.demo.model.Products;
import com.stg.demo.reponsitory.ProductsResponsitory;

import org.springframework.beans.factory.annotation.Autowired;
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
    @DeleteMapping("/api/products/{id}")
	public void delete(@PathVariable("id") int id) {
        productsResponsitory.deleteById(id);
	}

	@PostMapping("/api/products")
	public Products insert(@RequestBody Products products) {
		return productsResponsitory.save(products);
	}

	@PutMapping("/api/products")
	public Products update(@RequestBody Products products) {
		return productsResponsitory.save(products);
	}
}
