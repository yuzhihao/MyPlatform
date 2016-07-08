package com.zhihao.platform.service;

import com.zhihao.platform.data.entity.User;



public interface LoginService {
	public boolean validate(String username,String password);
	
	public User getUserByName(String username);

	public User getUserById(int userId);
	
	public boolean isEmailRepeat(String email);
	
	public boolean regist(String username, String password, String nickname);
	
	public boolean updateUser(User user);
}
