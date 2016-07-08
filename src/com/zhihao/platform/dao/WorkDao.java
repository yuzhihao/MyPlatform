package com.zhihao.platform.dao;

import java.util.List;

import com.zhihao.platform.data.entity.Work;

public interface WorkDao extends BaseDao<Work>{

	List<Work> getWorks(String columnName, int userId, int workType);
}
