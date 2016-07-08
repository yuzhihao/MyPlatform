package com.zhihao.platform.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihao.platform.dao.UserDao;
import com.zhihao.platform.dao.impl.UserDaoImpl;
import com.zhihao.platform.data.entity.User;
import com.zhihao.platform.service.LoginService;
import com.zhihao.platform.util.StaticVar;



@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDao userDao = new UserDaoImpl();

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public boolean validate(String username, String password) {
		System.out.println("validate called  . username is " + username);
		try {
			return userDao != null && userDao.validate(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public User getUserByName(String username) {
		if(userDao!=null){
			List<User> list = userDao.getBy("username",username);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
			else 
				return null;
		}
		return null;
	}

	@Override
	public User getUserById(int userId) {
		if(userDao!=null){
			List<User> list = userDao.getBy("id",userId);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
			else 
				return null;
		}
		return null;
	}

	@Override
	public boolean isEmailRepeat(String email) {
		List<User> list = userDao.getBy("username", email);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean regist(String username, String password, String nickname) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(nickname);
		user.setPhotopath(StaticVar.Default_PhotoPath);
		userDao.save(user);
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		if(user.getId()<=0){
			user = userDao.getBy("username", user.getUsername()).get(0);
		}
		return userDao.update(user, user.getId());
	}
}
