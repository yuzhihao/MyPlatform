package com.zhihao.platform.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihao.platform.dao.BlogDao;
import com.zhihao.platform.dao.CommentDao;
import com.zhihao.platform.dao.UserDao;
import com.zhihao.platform.data.entity.Comment;
import com.zhihao.platform.data.entity.User;
import com.zhihao.platform.service.CommentService;
import com.zhihao.platform.util.StaticVar;



@Service
public class CommentServiceImpl implements CommentService{

	
	@Autowired
	private CommentDao commentDao ;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public BlogDao getBlogDao() {
		return blogDao;
	}

	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}
	@Override
	public void addComment(Comment comment) {
		comment.setDate(new Date());
		//评论数加1
		if(commentDao.save(comment)>0){
			int commentCount = (int) blogDao.getColumnBy("id",  comment.getBlogId(),"commentcount");
			blogDao.updateColumn("commentcount", commentCount+1, (int) comment.getBlogId())
			;
		}
	}


	@Override
	public List<Comment> getCommentsByBlogId(int id) {
		return commentDao.getBy("blogId", id);
	}

	@Override
	public void processPhotoPath(List<Comment> comments) {
		Comment temp = null;
		List<User> tempUserList = null;
		for(int i=0;i<comments.size();i++){
			temp = comments.get(i);
			//有userid
			if(temp.getUserId()>0){
				//获取该user对象，并获取photopath
				tempUserList = userDao.getBy("id", (int)temp.getUserId());
				if(tempUserList!=null && tempUserList.size()>0)
					temp.setPhotopath(tempUserList.get(0).getPhotopath());
			}
			//无userid
			else{
				//设置默认头像
				temp.setPhotopath(StaticVar.Default_PhotoPath);
			}
		}
		
	}



}
