package com.icss.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import com.icss.bean.DepartmentsBean;


public interface DepartmentsDao {

	public int addDept(DepartmentsBean dBean) throws Exception;
	
	public int delDept(int id) throws Exception;
	
	public ResultSet queryDepById(int id) throws Exception;
	
	public int updDept(DepartmentsBean dBean) throws Exception;
	
	public Vector<Vector<Object>> queryAll() throws Exception;
	
	public List<DepartmentsBean> queryAllData() throws Exception;

}
