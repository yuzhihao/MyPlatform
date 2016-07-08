package com.zhihao.platform.data.entity;

public class User{
	
	public int id;
	
	public String username;

	public String password;

	private String address;
	
	private String telephone;
	
	private String photopath;
	
	private String nickname;
	
	private String website;
	
	private String interest;
	
	private String signature;
	
	private int sex;
	
	public User(){
		
	}
	
	//测试代码 
	public User(int id) {
		id = 1;
		username = "yuzhihao";
		address = "深圳宝安";
		telephone = "13622367764";
		photopath = "photo/xixi.jpg";
		nickname = "好吃鱼";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void checkNickName() {
		if(nickname==null || nickname.equalsIgnoreCase("")){
			nickname= username;
		}
		
	}

}
