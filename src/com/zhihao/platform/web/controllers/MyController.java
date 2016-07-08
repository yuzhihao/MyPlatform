package com.zhihao.platform.web.controllers;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//! session 中存储的 键值对 Key  -------start-----
	public static final String STATUS = "status";
	public static final String USERNAME = "username";
	public static final String NICKNAME = "nickname";
	//记录当前用户浏览的博客主 id =.=默认当然是我啦
	public static final String MASTERID = "masterId";
	
	//! session 中存储的 键值对 Key  ------- end -----
	
	public static final String STATUS_LOGIN = "status_login";
	public static final String STATUS_LOGOUT = "status_logout";
	
	//result type
	public static final String JSON ="json";
	
	/**
	 * 通用json数据构造Map
	 */
	private HashMap<String,Object> returnJsonMap = new HashMap<String ,Object>();
	
	
	protected  Logger logger;
	
	public MyController(){
		logger = LogManager.getLogger(getClass().getName());
	}

	public HashMap<String,Object> getReturnJsonMap() {
		return returnJsonMap;
	}

	public void setReturnJsonMap(HashMap<String,Object> returnJsonMap) {
		this.returnJsonMap = returnJsonMap;
	}
}
