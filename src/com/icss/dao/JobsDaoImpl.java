package com.icss.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.icss.bean.JobsBean;

public class JobsDaoImpl extends BaseDao implements JobsDao{

	@Override
	public int addJob(JobsBean jbean) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" INSERT INTO jobs(job_id,job_title,min_salary,max_salary) ")
				.append(" values(?,?,?,?) ")
				;
		return executeUpdate(sql.toString(),jbean.getJob_id(),
				jbean.getJob_title(),
				jbean.getMin_salary(),
				jbean.getMax_salary());
	}	

	@Override
	public int delJob(String id) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" DELETE　FROM JOBS ")
				.append(" WHERE job_id=? ")
				;
		
		return executeUpdate(sql.toString(), id);
	}	

	@Override
	public int update(JobsBean jbean) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append("  update jobs set ")
				.append(" job_title=?, ")
				.append(" min_salary=?, ")
				.append(" max_salary=? ")
				.append(" WHERE job_id=? ")
				;
		return executeUpdate(sql.toString(), jbean.getJob_title(),
				jbean.getMin_salary(),jbean.getMax_salary(),jbean.getJob_id());
	}

	@Override
	public List<JobsBean> queryAllJobs() throws Exception {
		List<JobsBean> allData = new ArrayList<JobsBean>();
		ResultSet rs = null;
		rs = executeQuery(" SELECT job_id,job_title,min_salary,max_salary FROM jobs ");
		while (rs.next()) {
			JobsBean jBean = new JobsBean();
			jBean.setJob_id(rs.getString(1));
			jBean.setJob_title(rs.getString(2));
			jBean.setMin_salary(rs.getDouble(3));
			jBean.setMax_salary(rs.getDouble(4));
			allData.add(jBean);		
		}
		return allData;
	}

	@Override
	public ResultSet queryJobsById(String id) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" SELECT job_id,job_title,min_salary,max_salary FROM jobs ")
				.append(" WHERE job_id=? ")
				;
		return executeQuery(sql.toString(), id);
	}

	@Override
	public boolean deljobByBatch(Object[] paramsList) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" delete from jobs  ")
				.append(" where job_id=? ")
				;
		return executeUpdateByBatch(sql.toString(),  paramsList);
	}
	
}
