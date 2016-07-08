package com.zhihao.platform.service;

import java.util.List;

import com.zhihao.platform.data.entity.Comment;



public interface CommentService {

	void addComment(Comment comment);

	List<Comment>  getCommentsByBlogId(int id);

	void processPhotoPath(List<Comment> comments);
	
	

}
