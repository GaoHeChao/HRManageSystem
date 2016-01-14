package com.icss.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface EmpsDao {

	public List<Map<String, Object>> queryAllEmps() throws Exception;

}
