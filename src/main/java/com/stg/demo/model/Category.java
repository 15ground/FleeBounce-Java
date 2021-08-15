package com.stg.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "categories")
public class Category {
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "id")
	 int id;

	@NotNull(message = "Không để trống")
	@NotBlank(message = "Tên danh mục không được để trống")
	@Size(max = 30, message = "Tên danh mục không được quá 30 ký tự!")
	@Column(name = "name")
	 String name;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
	 List<Products> products;

}	
