package com.stg.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	@NotBlank(message = "Địa chỉ không được để trống!")
	private String address;
	@NotNull(message = "Email không được để trống!")
	@Pattern(regexp = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$", message = "Sai định dạng email!")
	private String email;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
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
}
