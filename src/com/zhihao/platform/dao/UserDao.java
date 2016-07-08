package com.zhihao.platform.dao;

import java.sql.SQLException;

import com.zhihao.platform.data.entity.User;


public interface UserDao extends BaseDao<User>{
	public boolean validate(String username, String password) throws SQLException;
}
