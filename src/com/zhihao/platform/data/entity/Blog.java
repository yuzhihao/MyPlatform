package com.zhihao.platform.data.entity;

import java.util.Date;
public class Blog {
	private int id;
	
	private  Date date;

	private String title="";
	
	private String content="";
	
	private String contentShort;
	
	/**
	 * 观看数
	 */
	private int viewcount = 0 ;
	/**
	 * 点赞数
	 */
	private int markcount = 0;
	/**
	 * 评论数
	 */
	private int commentcount = 0;
	
	// master id of the blog
	private int userId;
	
	private String username;
	
	/**
	 * 1  技术分享
	 * 2  网络文摘
	 * 3  鸡汤
	 */
	private int blogType;
	
	/**
	 * 是否是原创作品
	 * 1  是 （默认）
	 * 0  不是
	 */
	private int isOriginal;

	public Blog(){
	}
	
	public Blog(String title, String content){
		setTitle(title);
		setContent(content);
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getBlogType() {
		return blogType;
	}

	public void setBlogType(int blogType) {
		this.blogType = blogType;
	}

	public int getIsOriginal() {
		return isOriginal;
	}

	public void setIsOriginal(int isOriginal) {
		this.isOriginal = isOriginal;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public int getMarkcount() {
		return markcount;
	}

	public void setMarkcount(int markcount) {
		this.markcount = markcount;
	}

	public String getContentShort() {
		return contentShort;
	}

	public void setContentShort(String contentShort) {
		this.contentShort = contentShort;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommentcount() {
		return commentcount;
	}

	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}
	
}
