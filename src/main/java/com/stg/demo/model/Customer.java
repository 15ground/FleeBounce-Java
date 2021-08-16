package com.stg.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name = "customer")
public class Customer {
	@Id
	@GeneratedValue
	private int id;

	@NotBlank(message = "Tên khách hàng không được để trống!")
	@Size(max = 50, message = "Tên khách hàng không được nhiều hơn 50 ký tự!")
	private String name;

	@NotNull
	@NotBlank(message = "Số điện thoại không được để trống")
	@Size(max = 10, message = "Số điện thoại không được quá 10 ký tự!")
	private String phoneNumber;

	@NotNull
	@NotBlank(message = "Địa chỉ nhận hàng không được để trống!")
	private String address;

	@NotNull(message = "Email không được để trống!")
	@Pattern(regexp = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$", message = "Sai định dạng email!")
	private String email;

	@NotNull
	@Size(min = 6, message = "Mật khẩu phải trên 5 ký tự!")
	private String password;

	String role = "user";

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Order> order;

	public Customer() {
		
	}

	public Customer(int id, String name, String phoneNumber, String address, String email) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
}
