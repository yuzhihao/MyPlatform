package com.zhihao.platform.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.zhihao.platform.dao.BaseDao;
import com.zhihao.platform.util.SqlHelper;
import com.zhihao.platform.util.SqlHelper.MyColumn;



@Repository
public class BaseDaoImpl<T> implements BaseDao<T>{

	
	
	protected  Logger logger;
	
	private  Class<T> cls;  
	  
    public void setCls(Class<T> cls){
    	this.cls =cls; 
    }
	
	public BaseDaoImpl(){
		logger = LogManager.getLogger(getClass().getName());
	}
	
	public List<MyColumn> createPreparedSql(StringBuffer sql, Object obj,String additional){
		List<MyColumn> columns = new ArrayList<MyColumn>();
		String sqlEmpty = "select * from "  + SqlHelper.DB_SCHEMA + removePath(cls.getName()).toLowerCase() +" where 1=0";
		//从数据库中获取该表的栏位信息
		Connection con = SqlHelper.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsm = null ;
		int columnCount = 0;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlEmpty);
			rsm = rs.getMetaData();
			columnCount = rsm.getColumnCount();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		boolean isFirst = true;
		Field[] fields = cls.getDeclaredFields();
		int index = 1;
		for(int i = 0; i<fields.length; i++){
			Field field = fields[i];
			String fieldName = field.getName();
			for (int j = 1; j <= columnCount; j++) {
				String columnName = null;
				String columnType = null;
				try {
					columnName = rsm.getColumnName(j);
					columnType = rsm.getColumnTypeName(j);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (fieldName.equalsIgnoreCase(columnName)) {
					field.setAccessible(true);
					MyColumn tempColumn = new MyColumn(index);
					index++;
					field.setAccessible(true);
					if( columnType.equalsIgnoreCase("BLOB")){
						try {
							if(field.get(obj)==null)
								tempColumn.value = "";
							else
								tempColumn.value = (String)field.get(obj);
							tempColumn.type = MyColumn.T_BLOB;
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
							break;
						}
					}
					else if (field.getType().equals(String.class) ){
						try {
							if(field.get(obj)==null)
								tempColumn.value = "";
							else
								tempColumn.value = (String)field.get(obj);
							tempColumn.type = MyColumn.T_STR;
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
							break;
						}
					}
					else if (field.getType().equals(int.class)){
						try {
							tempColumn.value = field.getInt(obj);
							tempColumn.type = MyColumn.T_INTEGER;
						}catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
							break;
						}
					}
					else if (field.getType().equals(long.class)){
						try {
							tempColumn.value = field.getLong(obj);
							tempColumn.type = MyColumn.T_LONG;
						}catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
							break;
						}
					}
					else if (field.getType().equals(Date.class)){
						try{
							//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							//tempColumn.value = sdf.format((Date) field.get(obj));
							//tempColumn.value = new java.sql.Date(((Date) field.get(obj)).getTime());
							tempColumn.value = (Date) field.get(obj);
							tempColumn.type = MyColumn.T_DATE;
						}catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
							break;
						}
					}else{
						continue;
					}
					if(!isFirst)
						sql.append(", ");
					sql.append(field.getName()+additional);
					isFirst = false;
					columns.add(tempColumn);
					break;
				}
			}
		}
		SqlHelper.close(con, stmt, rs);
		return columns;
	}
	
	@Override
	public Object getColumnBy(String columnName, Object value, String column) {
		String sql = "select "+column + " from "+ SqlHelper.DB_SCHEMA + removePath(cls.getName()).toLowerCase() + " where " + columnName +"=?";
		Connection con = SqlHelper.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Object result = null;
		try{
			pstmt =  con.prepareStatement(sql);
			pstmt.setObject(1, value);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				result = rs.getObject(1);
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}finally{
			SqlHelper.close(con, pstmt, rs);
		}
		return result;
	}

	@Override
	public boolean updateColumn(String colName, Object value, int id) {
		StringBuffer sql = new StringBuffer( "update " + SqlHelper.DB_SCHEMA + removePath(cls.getName()).toLowerCase() + " set "+colName +"=? where id="+id );
		
		Connection con = SqlHelper.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try{
			pstmt =  con.prepareStatement(sql.toString());
			pstmt.setObject(1, value);
			logger.debug(sql);
			rs = pstmt.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}finally{
			SqlHelper.close(con, pstmt, null);
		}
		if(rs>0)
			return true;
		else 
			return false;
	}
	
	@Override
	public boolean update(Object obj, int id){
		
		StringBuffer sql = new StringBuffer( "update " + SqlHelper.DB_SCHEMA + removePath(cls.getName()).toLowerCase() + " set " );
		
		List<MyColumn> columns = createPreparedSql(sql,obj,"=? ");
		sql.append(" where id =? ");
		columns.add(new MyColumn(columns.size()+1,MyColumn.T_INTEGER,id));
		logger.debug(sql);
		SqlHelper.executeIdentity(sql.toString(), columns);
		return true;
		
	}

	@Override
	public int save(Object obj) {
		StringBuffer sql = new StringBuffer( "insert into " + SqlHelper.DB_SCHEMA + removePath(cls.getName()).toLowerCase() + " (");
		List<MyColumn> columns = createPreparedSql(sql, obj, "");
		sql.append(") values(");
		boolean bIsFirst = true;
		for(int i=0;i<columns.size();i++){
			if(!bIsFirst){
				sql.append(",");
			}else{
				bIsFirst = false;
			}
			sql.append("?");
		}
		sql.append(")");
		logger.debug(sql);
		return SqlHelper.executeIdentity(sql.toString(),columns);
	}

	@Override
	public List<T> getBy(String columnName, String value) {
		return getBySql(gen_sql_getby(columnName), new MyColumn(1, MyColumn.T_STR,value));
	}
	
	@Override
	public List<T> getBy(String columnName, int value) {
		return getBySql(gen_sql_getby(columnName), new MyColumn(1, MyColumn.T_INTEGER,value));
	}
	
	@Override
	public List<T> getByCondition(String columnName, int value, String sql) {
		String tempSql = gen_sql_getby(columnName) + sql;
		return getBySql( tempSql,new MyColumn(1,MyColumn.T_INTEGER,value));
	}
	
	
	
	private String gen_sql_getby(String columnName){
		String sql = "select * from " + SqlHelper.DB_SCHEMA + removePath(cls.getName()).toLowerCase() 
				+ " where " + columnName + " = ?";
		return sql;
	}
	
	public List<T> getBySql(String sql, MyColumn param ){
		Connection con = SqlHelper.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<T> result = new ArrayList<T>();
		try {
			pstmt =  con.prepareStatement(sql);
			switch(param.type){
			case MyColumn.T_INTEGER:
				pstmt.setInt(param.index, (int) param.value);
				break;
			case MyColumn.T_STR:
				pstmt.setString(param.index, (String) param.value);
				break;
			}
			logger.debug(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				T temp = null;
				rs.getMetaData();
				temp = cls.newInstance();
				temp = SqlHelper.executeResultSet(cls, rs);
				result.add(temp);
			}
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}finally{
			SqlHelper.close(con, pstmt, rs);
		}
		return result;
	}
	
	public String removePath(String name ){
		return name.substring(name.lastIndexOf(".")+1);
	}

	@Override
	public boolean delete(int id) {
		String sql = "delete from "+SqlHelper.DB_SCHEMA + removePath(cls.getName()).toLowerCase() +" where id=?";
		Connection con = SqlHelper.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SqlHelper.close(con, pstmt, null);
		}
		return result;
	}

	


	

	

	


}
