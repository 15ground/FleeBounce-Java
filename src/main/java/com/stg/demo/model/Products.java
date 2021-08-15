package com.stg.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stg.demo.validate.CheckID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Products {
	@Id
	@GeneratedValue
	int id;

	@NotBlank(message = "Tên sản phẩm không được để trống!")
	@Size(max = 50, message = "Tên sản phẩm không được quá 50 ký tự!")
	String name;

	@NotNull(message = "Không để trống")
	@Pattern(regexp = "https?:\\/\\/.*\\.(?:png|jpg)", message = "Sai định dạng hình ảnh")
	String images;

	@NotNull(message = "Không để trống")
	Date created = new Date((new java.util.Date()).getTime());
	
	@NotNull(message = "Giá sản phẩm không được để trống!")
	@Min(1000)
	@Max(10000000)
	double price;

	String description;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "id"))
	Category category;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "products", cascade = CascadeType.ALL)
	 List<OrderItems> invoice_items;

	
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
