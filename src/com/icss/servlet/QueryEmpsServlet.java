package com.icss.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.dao.EmpsDao;
import com.icss.dao.EmpsFactory;

@WebServlet("/QueryEmpsServlet")
public class QueryEmpsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueryEmpsServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		EmpsDao dao = EmpsFactory.getInstance();
		List<Map<String, Object>> allEmps = new ArrayList<>();
		
		try {
			allEmps = dao.queryAllEmps();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("allEmp", allEmps);
		request.getRequestDispatcher("admin/EmpViewServlet.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
