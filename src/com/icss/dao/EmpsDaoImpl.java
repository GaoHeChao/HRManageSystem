package com.icss.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.icss.bean.EmpsBean;

public class EmpsDaoImpl extends BaseDao implements EmpsDao {

	@Override
	public List<Map<String, Object>> queryAllEmps() throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" SELECT * ")
				.append("   FROM  employees e,departments d,jobs j ")
				.append("  where e.department_id=d.department_id ")
				.append("    and e.job_id=j.job_id ")
				;
		return executeQueryByMap(sql.toString());
	}

	/**
	 * 对oracle函数的应用还是很不熟悉，需要巩固
	 */
	@Override
	public int addEmp(EmpsBean eBean) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" INSERT INTO employees(emp_name,email,phone_number,hire_date,job_id,salary,department_id) ")
				.append(" values(?,?,?,to_date('"+eBean.getHire_date()+"','yyyy-mm-dd'),?,?,?) ")
				;
		
		return executeUpdate(sql.toString(), eBean.getEmp_name(),
				eBean.getEmail(),eBean.getPhone_number(),
				eBean.getJob_id(),eBean.getSalary(),eBean.getDepartment_id());
	}

	@Override
	public ResultSet queryEmpById(int id) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" select * from employees ")
				.append(" WHERE employee_id=? ")
				;
		return executeQuery(sql.toString(), id);
	}

	@Override
	public int delEmp(int id) throws Exception {
		
		return executeUpdate(" delete from employees where employee_id=? ", id);
	}

	@Override
	public int updateEmp(EmpsBean eBean) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" update employees ")
				.append(" set email=?, ")
				.append(" phone_number=?, ")
				.append(" hire_date=?, ")
				.append(" job_id=? ")
				.append(" salary=? ")
				.append(" department_id=? ")
				.append(" where employee_id=? ")
				;
		return executeUpdate(sql.toString(), eBean.getEmail(),eBean.getPhone_number(),
				eBean.getHire_date(),eBean.getJob_id(),eBean.getSalary(),
				eBean.getDepartment_id(),eBean.getEmployee_id());
	}


}
