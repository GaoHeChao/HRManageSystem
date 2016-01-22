package com.icss.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.bean.DepartmentsBean;
import com.icss.dao.DepartmentsDao;
import com.icss.dao.DepartmentsFactory;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

@WebServlet("/QueryDeptServlet")
public class QueryDeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueryDeptServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		DepartmentsDao dao = DepartmentsFactory.getInstance();
		/*
		 * 方法一
		 */
//		try {
//			Vector<Vector<Object>>  oVectors = dao.queryAll();
//			Iterator<Vector<Object>> it = oVectors.iterator();
//			while (it.hasNext()){
//				Vector<Object> oneRow = it.next();
//				System.out.println(oneRow);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		/*
		 * 方法二
		 */
		List<DepartmentsBean> allData = new ArrayList<>();
		try {
			allData = dao.queryAllData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("Data", allData);	
		request.getRequestDispatcher("/admin/DeptViewServlet.jsp")
		.forward(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		doGet(request, response);
	}

}
