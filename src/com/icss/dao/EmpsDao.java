package com.icss.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.icss.bean.EmpsBean;

public interface EmpsDao {

	public List<Map<String, Object>> queryAllEmps() throws Exception;
	
	public int addEmp(EmpsBean eBean) throws Exception;
	
	public int delEmp(int id) throws Exception;
	
	public int updateEmp(EmpsBean eBean) throws Exception;
	
	public ResultSet queryEmpById(int id) throws Exception;

}
