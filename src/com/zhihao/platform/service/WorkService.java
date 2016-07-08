package com.zhihao.platform.service;

import java.util.List;

import com.zhihao.platform.data.entity.Work;
import com.zhihao.platform.util.PageManager;



public interface WorkService {
	public List<Work> listWork(int userId, int workType, PageManager pm);

	public void addWork(Work newWork);

	public Work getWork(int id);

	public void deleteBlog(int workId);

	public void updateWork(Work newWork);
}
