package com.zhihao.platform.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihao.platform.dao.BlogDao;
import com.zhihao.platform.data.entity.Blog;
import com.zhihao.platform.data.entity.User;
import com.zhihao.platform.service.BlogService;
import com.zhihao.platform.util.PageManager;



@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogDao blogDao;
	
	@Override
	public boolean writeBlog(Blog blog, User user) {
		Date date = new Date();
		blog.setDate(date);
		blog.setUserId(user.getId());
		blogDao.save(blog);
		return true;
	}

	@Override
	public void modifyBlog(Blog blog) {
		blogDao.update(blog, blog.getId());
		
	}
	
	@Override
	public List<Blog> listBlog(int userId, int blogType,PageManager pm) {
		List<Blog> list = blogDao.getBlog("userId", userId, blogType);
		pm.setRecordCount(list.size());
		List<Blog> resultList = new ArrayList<Blog>();
		int start = (pm.getPageNo()-1) * pm.getPageSize();
		for(int i=0;i<pm.getPageSize();i++){
			if((start+i)>=list.size())
				break;
			resultList.add(list.get(start+i));
		}
		return resultList;
		
	}

	@Override
	public Blog getBlog(int parameter) {
		// TODO Auto-generated method stub
		List<Blog> list = blogDao.getBy("id", parameter);
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public void viewBlog(int id) {
		int viewcount = (int) blogDao.getColumnBy("id", id, "viewcount");
		blogDao.updateColumn("viewcount", viewcount+1, id);
	}

	@Override
	public int zan(int blogId,int userId) {
		return blogDao.zan(blogId,userId);
	}

	@Override
	public void deleteBlog(int blogId) {
		blogDao.delete(blogId);
		
	}

	

}
