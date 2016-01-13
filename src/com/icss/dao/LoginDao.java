package com.icss.dao;

import java.sql.ResultSet;

import org.apache.tomcat.websocket.server.WsHandshakeRequest;

public class LoginDao extends BaseDao {

	public ResultSet queryUser(String name,String pwd, int level) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" Select adminname,adminpwd,adminlevel from admin ")
				.append(" where adminname=?  ")
				.append(" and adminpwd=?  ")
				.append(" and adminlevel=?  ")
				;
		
		return executeQuery(sql.toString(), name,pwd,level);
	}
}
