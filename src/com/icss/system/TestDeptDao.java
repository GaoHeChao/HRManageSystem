package com.icss.system;

import java.util.Iterator;
import java.util.List;

import com.icss.bean.JobsBean;
import com.icss.dao.JobsDao;
import com.icss.dao.JobsFactory;

public class TestDeptDao {

	public static void main(String[] args) {
		JobsDao dao = JobsFactory.getInstance();

		
		
		try {
			List<JobsBean> allJobs = dao.queryAllJobs();
			Iterator<JobsBean> it = allJobs.iterator();
			while(it.hasNext()){
	             JobsBean bean = it.next();
	             System.out.println(bean.getJob_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
