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
	//��Connection�뱾���̰߳�
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private static final String USER = "cc";
	private static final String PASSWORD = "cc";
	
	//�����ݿ�������ض��������
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	// ������
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException{
		// �ȴӱ����߳��л����ݿ����Ӷ���
		conn = threadLocal.get();
		// �ж϶����Ƿ�Ϊ��
		if(conn == null){
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			// �����Ӷ�����ӵ������߳���
			threadLocal.set(conn);
		}
		return conn;
	}
	/**
	 * �ر����ӵķ���
	 */
	private void close() throws Exception{
		// �ȴӱ����߳��л����ݿ����Ӷ���
		conn = threadLocal.get();
		if(conn != null && !conn.isClosed()){
			conn.close();	//������Ȼ�ر��ˣ������Ӷ�����Ȼ����
			threadLocal.remove(); //�ӱ����߳���������Ӷ���
			conn = null;	//������Ӷ���
		}
	}	
	//5 �ر�
	/**
	 * �ر���ݿ�������ض���
	 * �ṩ������ʹ��
	 * @throws Exception 
	 */
	protected void closeAll() throws Exception{
		if(rs != null){
			rs.close();	//�رս��
			rs = null;
		}
		if(pstm != null){
			pstm.close();
			pstm = null;
		}
		close();  //�ر�����
	}
	
	/**
	 * �����ɾ�ķ���
	 * @param sql  
	 * @param param
	 * @return �����Ӱ�������
	 * @throws Exception 
	 */
	protected int executeUpdate(String sql, Object... param) throws Exception {
		// ִ�У�try-finally
		try {
			// ��������
			conn = getConnection();
			// Ԥ����SQL������
			pstm = conn.prepareStatement(sql.toString());
			//ͨ��ɱ䳤����ѭ����ɣ���ֵ
			for(int i=0;i<param.length;i++)
			{
				pstm.setObject(i+1, param[i]);
			}
			// ִ�в�������
			return pstm.executeUpdate();
		} finally {
			closeAll();
		}
	}
	/**
	 * ִ�в�ѯ���
	 * @param sql
	 * @param param
	 * @return ��ǰ��ѯ�õ��Ľ��
	 * @throws Exception
	 */
	protected ResultSet executeQuery(String sql,Object...param)throws Exception{
		
		conn = getConnection();
		pstm = conn.prepareStatement(sql.toString());
		for(int i=0;i<param.length;i++){
			pstm.setObject(i+1, param[i]);
		}
		rs = pstm.executeQuery();
		//�ѹرյĲ����Ÿ�������	
		return rs;
	}
	/**
	 * ��ѯ�����ؽ����������װΪ��������ݷ�յ�Vector,�ٽ���������ݷ�װVector
	 * @param sql
	 * @param param
	 * @return
	 * @throws Exception
	 */
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
			//������ݷ�յ�Vector
			Vector<Object> oneRow = new Vector<Object>();
			for(int i=0;i<colCount;i++){
				oneRow.add(rs.getObject(i+1));
			}
			//��������ݷ�װVector
			allData.add(oneRow);
		}
		return allData;
	}
	/**
	 * ��ѯ���е�ֵ��ݣ�SUM COUNT MAX MIN AVG 
	 * @param sql
	 * @param param
	 * @return ��ֵ����
	 * @throws SQLException
	 */
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
