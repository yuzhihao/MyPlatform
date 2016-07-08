package com.zhihao.platform.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.zhihao.platform.dao.UserDao;
import com.zhihao.platform.data.entity.User;
import com.zhihao.platform.util.SqlHelper;



@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	public UserDaoImpl(){
		setCls(User.class);
	}

	@Override
	public boolean validate(String username, String password) throws SQLException {
		Connection con = SqlHelper.getConnection();
		Statement stmt = con.createStatement();
		String sql = "select * from user where username = N'" + username + "' and password = N'" + password + "'" ;
		logger.debug(sql);
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			stmt.close();
			con.close();
			return true;
		}
		return false;
	}

}
