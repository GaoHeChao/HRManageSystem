package com.icss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class BaseDao {

	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private static final String USER = "cc";
	private static final String PASSWORD = "cc";

	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException{
		conn = threadLocal.get();
		if(conn == null){
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			threadLocal.set(conn);
		}
		return conn;
	}

	private void close() throws Exception{
		conn = threadLocal.get();
		if(conn != null && !conn.isClosed()){
			conn.close();
			threadLocal.remove(); 
			conn = null;	
		}
	}	

	protected void closeAll() throws Exception{
		if(rs != null){
			rs.close();	
			rs = null;
		}
		if(pstm != null){
			pstm.close();
			pstm = null;
		}
		close();  
	}

	protected int executeUpdate(String sql, Object... param) throws Exception {
		
		try {
			conn = getConnection();
			pstm = conn.prepareStatement(sql.toString());
			for(int i=0;i<param.length;i++)
			{
				pstm.setObject(i+1, param[i]);
			}
			return pstm.executeUpdate();
		} finally {
			closeAll();
		}
	}
	protected ResultSet executeQuery(String sql,Object...param)throws Exception{
		
		conn = getConnection();
		pstm = conn.prepareStatement(sql.toString());
		for(int i=0;i<param.length;i++){
			pstm.setObject(i+1, param[i]);
		}
		rs = pstm.executeQuery();
		return rs;
	}

	protected Vector<Vector<Object>> executeQueryVector(String sql,Object...param)throws Exception{
		Vector<Vector<Object>> allData = new Vector<Vector<Object>>();
		conn = getConnection();
		pstm = conn.prepareStatement(sql.toString());
		for(int i=0;i<param.length;i++){
			pstm.setObject(i+1, param[i]);
		}
		rs = pstm.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();
		while(rs.next()){
			Vector<Object> oneRow = new Vector<Object>();
			for(int i=0;i<colCount;i++){
				oneRow.add(rs.getObject(i+1));
			}
			allData.add(oneRow);
		}
		return allData;
	}

	protected Object executeScalar(String sql, Object... param) throws Exception
	{
		Object obj = null;
		
		try{
			conn = getConnection();
			pstm = conn.prepareStatement(sql);
			if(param != null)
			{
				for(int i=0;i<param.length;i++)
				{
					pstm.setObject(i+1, param[i]);
				}
			}
			rs = pstm.executeQuery();
			if(rs.next())
			{
				obj = rs.getObject(1);
			}
		}finally{
			closeAll();
		}
		return obj;
	}
	
}
