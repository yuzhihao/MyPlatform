package com.zhihao.platform.dao;

import java.util.List;

import com.zhihao.platform.data.entity.Blog;


public interface BlogDao extends BaseDao<Blog>{

	List<Blog> getBlog(String string, int userId,int blogType);


	/**
	 * 赞 
	 * @param blogId
	 * @param userId
	 * return int 被赞数
	 */
	int zan(int blogId, int userId);

}
