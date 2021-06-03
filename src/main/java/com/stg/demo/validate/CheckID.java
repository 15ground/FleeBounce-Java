package com.stg.demo.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CheckValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckID {
	public String message() default "Danh mục không tồn tại!";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
}
