package com.zhihao.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihao.platform.dao.WorkDao;
import com.zhihao.platform.data.entity.Work;
import com.zhihao.platform.service.WorkService;
import com.zhihao.platform.util.PageManager;

@Service
public class WorkServiceImpl implements WorkService{
	
	
	@Autowired
	private WorkDao workDao;
	@Override
	public List<Work> listWork(int userId, int workType, PageManager pm) {
		List<Work> list = workDao.getWorks("userId", userId, workType);
		pm.setRecordCount(list.size());
		List<Work> resultList = new ArrayList<Work>();
		int start = (pm.getPageNo()-1) * pm.getPageSize();
		for(int i=0;i<pm.getPageSize();i++){
			if((start+i)>=list.size())
				break;
			resultList.add(list.get(start+i));
		}
		return resultList;
	}
	@Override
	public void addWork(Work newWork) {
		workDao.save(newWork);
		
	}
	@Override
	public Work getWork(int id) {
		return workDao.getBy("id", id).get(0);
	}
	@Override
	public void deleteBlog(int workId) {
		workDao.delete(workId);
		
	}
	@Override
	public void updateWork(Work newWork) {
		// TODO Auto-generated method stub
		workDao.update(newWork, newWork.getId());
		
	}

}
