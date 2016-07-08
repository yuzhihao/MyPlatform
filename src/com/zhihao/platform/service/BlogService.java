package com.zhihao.platform.service;

import java.util.List;

import com.zhihao.platform.data.entity.Blog;
import com.zhihao.platform.data.entity.User;
import com.zhihao.platform.util.PageManager;



public interface BlogService {
	
	/**
	 * 新增博客
	 * @param blog
	 * @param user
	 * @return
	 */
	public boolean writeBlog(Blog blog, User user);
	
	/**
	 * 修改博客
	 * @param blog
	 */
	public void modifyBlog(Blog blog);
	
	public List<Blog> listBlog(int userId, int blogType, PageManager pm);

	/**
	 * 获取单个blog  点进blog详情页面时调用
	 * @param id
	 * @return
	 */
	public Blog getBlog(int parameter);
	
	/**
	 * 使blog的点击数+1 ， 点击blog时调用一次
	 * @param id
	 */
	public void viewBlog(int id);
	
	/**
	 * 赞  ， 传入被赞的博客id，点赞人的id,返回该博客被赞 的次数
	 * @param id
	 * @param id2 
	 * @return
	 */
	public int zan(int blogId, int userId);

	/**
	 * 删除blog
	 * @param blogId
	 */
	public void deleteBlog(int blogId);

	
}
