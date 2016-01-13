package com.icss.dao;

import java.sql.ResultSet;
import java.util.List;

import com.icss.bean.JobsBean;

public interface JobsDao {

	public int addJob(JobsBean jbean) throws Exception;
	
	public int delJob(String id) throws Exception;
	
	public List<JobsBean> queryAllJobs() throws Exception;
	
	public int update(JobsBean jbean) throws Exception;
	
	public ResultSet queryJobsById(String id) throws Exception;
}
