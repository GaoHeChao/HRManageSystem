package com.icss.dao;

import java.sql.ResultSet;

import com.icss.bean.PhotoBean;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class PhotoDao extends BaseDao{

	public int addPhoto(String photo_path,int employee_id) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" INSERT INTO photo(photo_path,employee_id) ")
				.append(" VALUES(?,?) ")
				;
		return executeUpdate(sql.toString(),photo_path, employee_id);
	}
	
	public int delPhoto(int photo_id) throws Exception{
		
		return executeUpdate(" DELETE FROM photo WHERE photo_id=? ", photo_id);
	}
	
	public ResultSet queryPhotosById(int emp_id) throws Exception{
	
		return executeQuery(" SELECT * FROM photo WHERE employee_id=? ",emp_id);
	}
}
