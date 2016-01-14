package com.icss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	protected List<Map<String, Object>> executeQueryByMap(String sql,Object... param) throws Exception{
		List<Map<String, Object>> allData = new ArrayList<>();
		try{
			conn = getConnection();
			pstm = conn.prepareStatement(sql.toString());
			for(int i=0; i<param.length; i++){
				pstm.setObject(i+1, param[i]);
			}
			rs=pstm.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while(rs.next()){
				Map<String, Object> item = new HashMap<>();
				for(int i=0; i<rsmd.getColumnCount(); i++){
					item.put(rsmd.getColumnName(i+1).toLowerCase(), 
							rs.getObject(rsmd.getColumnName(i+1)) );
				}
				allData.add(item);
			}
		}finally{
			closeAll();
		}
		return allData;
	}
	
	protected boolean executeUpdateByBatch(String sql,Object[] paramsList) throws Exception{
		boolean flag = true;
		try {
			conn = getConnection();
			pstm = conn.prepareStatement(sql);
			for(int i=0; i<paramsList.length; i++){
				pstm.setObject(1, paramsList[i]);
				pstm.addBatch();
			}
			conn.setAutoCommit(false);
			
			try {
				pstm.executeBatch();
				conn.commit();
				flag = true;
			} catch (Exception e) {
			    conn.rollback();
			    throw e;
			}finally {
				conn.setAutoCommit(true);
			}
			return flag;
		} finally {
			closeAll();
		}	
	}
	
	
	
	/**
	 * 分页----------------------------------------------------------------------
	 */
	private int rowCount;
	private int pageSize = 10;
	private int nowPage;
	private int pageCount;
	public int getRowCount() {
		return rowCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getNowPage() {
		return nowPage;
	}

	public int getPageCount() {
		return pageCount;
	}
	
	public List<Map<String, Object>> queryOnPage(String sql, int nowPage,int pageSize, Object...param)
	      throws Exception{
		List<Map<String, Object>> allData = new ArrayList<>();
		
		try {
			conn = getConnection();
			pstm = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0; i<param.length; i++){
				pstm.setObject(i+1, param[i]);
			}
			rs = pstm.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			rs.last();
			rowCount = rs.getRow();
			if(pageSize>0){
				this.pageSize = pageSize;
			}
			
			if(rowCount % this.pageSize == 0){
				pageCount = rowCount/this.pageSize;
			}else{
				pageCount = rowCount/this.pageSize+1;
			}
			
			//pageCount = (rowCount-1)/pageSize + 1;    //最优算法
			
			if(nowPage<1){
				this.nowPage = 1;
			}else if(nowPage>pageCount){
				this.nowPage = pageCount;
			}else{
				this.nowPage = nowPage;
			}
			
			if(nowPage == 1){
				rs.beforeFirst();
			}else{
				rs.absolute((this.nowPage-1)*pageSize);
			}
			
			for(int count=0; count<pageSize; count++){
				if(rs.next()){
					Map<String, Object> item = new HashMap<>();
					
					for(int i=0; i<rsmd.getColumnCount(); i++){
						item.put(rsmd.getColumnName(i+1).toLowerCase(), rs.getObject(rsmd.getColumnName(i+1)));
					}
					allData.add(item);
					
					
				}else{
					break;
				}
			}
	
		} finally {
			closeAll();
		}
		
		return allData;
	}
		
	
}
