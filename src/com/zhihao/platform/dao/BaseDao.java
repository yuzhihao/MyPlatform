package com.zhihao.platform.dao;

import java.util.List;

public interface BaseDao<T>{
	
	public static final String PROC_ZAN ="proc_zan";
	
	public int save(T obj);
	
	public boolean update(T obj,int id);
	
	
	public List<T> getBy(String byName, String byValue);
	
	public List<T> getBy(String byName, int byValue);
	
	public List<T> getByCondition(String byName, int byValue, String sql);
	
	public Object getColumnBy(String byName, Object byValue, String column);

	boolean updateColumn(String colName, Object value, int id);
	
	boolean delete(int id);
}
