package com.icss.system;

import java.util.Iterator;
import java.util.List;

import com.icss.bean.DepartmentsBean;
import com.icss.dao.DepartmentsDao;
import com.icss.dao.DepartmentsFactory;

public class TestDeptDao {

	public static void main(String[] args) {
		DepartmentsDao dao = DepartmentsFactory.getInstance();
		//dao.addDept(dBean);
		
		try {
			List<DepartmentsBean> allData = dao.queryAllData();
			Iterator<DepartmentsBean> it = allData.iterator();
			while (it.hasNext()) {
				DepartmentsBean bean = it.next();
				bean.getDepartment_id();
				bean.getDepartment_name();
				bean.getLocation_name();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
