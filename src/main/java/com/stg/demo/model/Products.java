package com.stg.demo.model;

import java.util.Date;

public class Products {
	int id;
	String name;
	String images;
	Date created;
	double price;
	String description;
	int categoryID;

	public Products() {
		super();
	}

	public Products(int id, String name, String images, Date created, double price, String description,
			int categoryID) {
		super();
		this.id = id;
		this.name = name;
		this.images = images;
		this.created = created;
		this.price = price;
		this.description = description;
		this.categoryID = categoryID;
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

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
}
