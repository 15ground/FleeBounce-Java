package com.stg.demo.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.stg.demo.model.Category;
import com.stg.demo.reponsitory.CategoriesReponsitory;

public class CheckValidator implements ConstraintValidator<CheckID, Category> {
	@Autowired
	CategoriesReponsitory categoriesResponsitory;

	@Override
	public boolean isValid(Category category, ConstraintValidatorContext context) {
		if (categoriesResponsitory == null)
			return true;
		if (categoriesResponsitory.findById(category.getId()).isEmpty()) {
			return false;
		}
		return true;
	}
}
