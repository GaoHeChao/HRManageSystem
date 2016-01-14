package com.icss.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

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


}
