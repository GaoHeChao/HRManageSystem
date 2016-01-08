package com.icss.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.bean.DepartmentsBean;
import com.icss.dao.DepartmentsDao;
import com.icss.dao.DepartmentsFactory;

@WebServlet("/UpdateDeptServlet")
public class UpdateDeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateDeptServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ServletContext servletContext = this.getServletContext();
		String encode = servletContext.getInitParameter("encoding");
		
		request.setCharacterEncoding(encode);
		response.setContentType("text/html;charset=utf-8");
		DepartmentsDao dao = DepartmentsFactory.getInstance();
		
		String dNum =request.getParameter("department_id");
		int dnum = Integer.parseInt(dNum);
		String dName = request.getParameter("department_name");
		String lName = request.getParameter("location_name");
		
		DepartmentsBean bean = new DepartmentsBean();
		bean.setDepartment_id(dnum);
		bean.setDepartment_name(dName);
		bean.setLocation_name(lName);
		String path = "QueryDeptServlet";
		try {
			int rows = dao.updDept(bean);
			if(rows>0){
				System.out.println("更新成功！");
			}else{
				path = "/admin/UpdateDept.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("dep_id", dnum);
		request.setAttribute("ben", bean);
		
		//更新失败的两种转法，1、请求转发，2、请求重定向
		request.getRequestDispatcher(path)
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
