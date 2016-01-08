package com.icss.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
	
	public int delDept(int id) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" delete from departments where department_id=? ")
				;
		return executeUpdate(sql.toString(), id);
	}

	@Override
	public ResultSet queryDepById(int id) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" SELECT department_id,department_name,location_name ")
				.append("  from departments ")
				.append("  where department_id=?  ")
				;

		return executeQuery(sql.toString(),id);
	}
	
	public int updDept(DepartmentsBean dBean) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" update departments  ")
				.append(" set department_name=?,location_name=?  ")
				.append(" where department_id=? ")
				;
		return executeUpdate(sql.toString(), dBean.getDepartment_name(),
				dBean.getLocation_name(),dBean.getDepartment_id());
	}

	//方法一
	@Override
	public Vector<Vector<Object>>  queryAll() throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" SELECT * FROM DEPARTMENTS ")
				;
		return executeQueryVector(sql.toString());
	}

	//方法二
	@Override
	public List<DepartmentsBean> queryAllData() throws Exception {
		List<DepartmentsBean>  allData = new ArrayList<DepartmentsBean>();
		ResultSet rs = null;
		rs = executeQuery(" SELECT department_id,department_name,location_name FROM DEPARTMENTS ORDER BY department_id ");
		
		try {
			while(rs.next()){
				DepartmentsBean dBean = new DepartmentsBean();
				dBean.setDepartment_id(rs.getInt(1));
				dBean.setDepartment_name(rs.getString(2));
				dBean.setLocation_name(rs.getString(3));
				allData.add(dBean);
			}
		} finally {
			closeAll();
		}	
		return allData;
	}

}
