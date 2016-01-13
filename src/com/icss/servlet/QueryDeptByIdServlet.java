package com.icss.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.bean.DepartmentsBean;
import com.icss.dao.DepartmentsDao;
import com.icss.dao.DepartmentsFactory;

@WebServlet("/QueryDeptByIdServlet")
public class QueryDeptByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueryDeptByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		DepartmentsDao dao = DepartmentsFactory.getInstance();
		
		String dept_id = request.getParameter("dept_id");
		int id = Integer.parseInt(dept_id);

		DepartmentsBean bean = new DepartmentsBean();
		
		try {
			ResultSet rs = rs = dao.queryDepById(id);
			while(rs.next()){
				bean.setDepartment_id(rs.getInt(1));
				bean.setDepartment_name(rs.getString(2));
				bean.setLocation_name(rs.getString(3));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("idBean", bean);
		request.getRequestDispatcher("admin/UpdateDept.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
