package com.icss.dao;

import com.icss.bean.DepartmentsBean;

public class DepartmentsDaoImpl extends BaseDao implements DepartmentsDao{

	public int addDept(DepartmentsBean dBean) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" INSERT INTO DEPARTMENTS ")
				.append(" VALUES(?,?,?) ")
				;
		return executeUpdate(sql.toString(), dBean.getDepartment_id(),
				dBean.getDepartment_name(),
				dBean.getLocation_name());
	}
}
