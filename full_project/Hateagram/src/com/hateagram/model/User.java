package com.hateagram.model;

public class User implements IUser {
	private String userName;
	private String realName;
	private int age;
	private String password;
	private String email;
	
	public User(String userName, String realName, int age,String password, String email) {
		this.userName = userName;
		this.realName = realName;
		this.age = age;
		this.email = email;
		this.password=password;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public String getRealName() {
		return realName;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public String getEmail() {
		return email;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	
}
