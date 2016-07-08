package com.zhihao.platform.data.entity;

public class LoginInfo {

	/**
	 * 登录是否成功
	 */
	boolean login;
	
	/**
	 * 用户名是否有效
	 */
	boolean isUserNameValid;
	
	/**
	 * 注册是否成功
	 */
	boolean regist;
	
	/**
	 * 注册邮箱是不是被占用
	 */
	boolean repeat;
	
	public boolean isRepeat() {
		return repeat;
	}
	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}
	public boolean isRegist() {
		return regist;
	}
	public void setRegist(boolean regist) {
		this.regist = regist;
	}
	public boolean isLogin() {
		return login;
	}
	public void setLogin(boolean isLogin) {
		this.login = isLogin;
	}
	public boolean isUserNameValid() {
		return isUserNameValid;
	}
	public void setUserNameValid(boolean isUserNameValid) {
		this.isUserNameValid = isUserNameValid;
	}
	
}
