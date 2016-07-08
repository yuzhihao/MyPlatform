package com.zhihao.platform.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhihao.platform.dao.WorkDao;
import com.zhihao.platform.data.entity.Work;


@Repository
public class WorkDaoImpl extends BaseDaoImpl<Work> implements WorkDao{

	public WorkDaoImpl(){
		setCls(Work.class);
	}
	
	@Override
	public int save(Work obj) {
		return super.save(obj);
	}

	@Override
	public boolean update(Work obj, int id) {
		return super.update(obj, id);
	}

	@Override
	public List<Work> getWorks(String columnName, int columnValue, int workType) {
		String sql = "";
		if(workType>0)
			sql = " and type = " + workType ;
		sql += " order by date desc";
		return getByCondition(columnName,columnValue,sql);
	}

}
