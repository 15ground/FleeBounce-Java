package com.stg.demo.model;

public class Login {

	private String phoneNumber;
	private String passWord;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Login(String phoneNumber, String passWord) {
		super();
		this.phoneNumber = phoneNumber;
		this.passWord = passWord;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
