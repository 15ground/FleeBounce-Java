package com.stg.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.stg.demo.validate.CheckID;

@Entity(name = "products")
public class Products {
	@Id
	@GeneratedValue
	int id;
	@NotBlank(message = "Tên sản phẩm không được để trống!")
	@Size(max = 50, message = "Tên sản phẩm không được quá 50 ký tự!")
	String name;
	@NotNull
	@Pattern(regexp = "https?:\\/\\/.*\\.(?:png|jpg)", message = "Sai định dạng hình ảnh")
	String images;
	@NotNull
	Date created = new Date((new java.util.Date()).getTime());
	@NotNull(message = "Giá sản phẩm không được để trống!")
	@Min(1000)
	@Max(10000000)
	double price;
	String description;
	@CheckID
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "category_products"))
	Category category;
	String pictures;

	public Products() {
		super();
	}

	public Products(int id, String name, String images, Date created, double price, String description) {
		super();
		this.id = id;
		this.name = name;
		this.images = images;
		this.created = created;
		this.price = price;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Products) {
			Products cProduct = (Products) obj;
			return this.id == cProduct.getId();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id;
	}
}
