package com.zhihao.platform.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhihao.platform.dao.BlogDao;
import com.zhihao.platform.data.entity.Blog;
import com.zhihao.platform.util.SqlHelper;
import com.zhihao.platform.util.SqlHelper.MyColumn;



@Repository
public class BlogDaoImpl  extends BaseDaoImpl<Blog> implements BlogDao{

	public BlogDaoImpl(){
		setCls(Blog.class);
	}

	@Override
	public int save(Blog obj) {
		logger.debug("Content Lenth: " + obj.getContent().length());
		return super.save(obj);
	}

	@Override
	public boolean update(Blog obj, int id) {
		return super.update(obj, id);
	}

	@Override
	public List<Blog> getBlog(String string, int userId, int blogType) {
		String sql = "";
		if(blogType>0)
			sql = " and blogType = " + blogType ;
		sql += " order by date desc";
		return getByCondition(string,userId,sql);
	}

	@Override
	public int zan(int blogId, int userId) {
		String sql = "call proc_zan(?,?,?)";
		List<MyColumn> params = new ArrayList<MyColumn>();
		params.add(new MyColumn(1, MyColumn.T_INTEGER, userId));
		params.add(new MyColumn(2, MyColumn.T_INTEGER, blogId));
		params.add(new MyColumn(3, MyColumn.T_INTEGER, null, MyColumn.PROC_PARAM_OUT));
		logger.debug("sql");
		List<Object> result = SqlHelper.executeProc(sql, params);
		
		return (int) result.get(0);
	}
	

}
