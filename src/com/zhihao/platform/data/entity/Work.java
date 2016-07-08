package com.zhihao.platform.data.entity;

import java.util.Date;

public class Work {

	private int id;
	private String name = "";
	private String description = "";
	private String res_path = "";
	private String src_path = "";
	private String doc_path = "";
	private String img_path = "";
	private int type = 2;
	private int userId;
	private Date date;
	private String tool = "";
	private String detail = "";
	
	public Work(){
		;
	}
	
	public Work(int userId){
		this.userId= userId;
		img_path="/works/cover/defaultCover.jpg";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRes_path() {
		return res_path;
	}
	public void setRes_path(String res_path) {
		this.res_path = res_path;
	}
	public String getSrc_path() {
		return src_path;
	}
	public void setSrc_path(String src_path) {
		this.src_path = src_path;
	}
	public String getDoc_path() {
		return doc_path;
	}
	public void setDoc_path(String doc_path) {
		this.doc_path = doc_path;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTool() {
		return tool;
	}
	public void setTool(String tool) {
		this.tool = tool;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
