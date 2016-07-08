package com.zhihao.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhihao.platform.dao.CommentDao;
import com.zhihao.platform.data.entity.Comment;



@Repository
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao{

	public CommentDaoImpl(){
		this.setCls(Comment.class);
	}
}
