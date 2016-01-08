package com.icss.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.dao.DepartmentsDao;
import com.icss.dao.DepartmentsFactory;

@WebServlet("/DelDeptServlet")
public class DelDeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DelDeptServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ServletContext servletContext = this.getServletContext();
		String encode = servletContext.getInitParameter("encoding");
		
		request.setCharacterEncoding(encode);
		response.setContentType("text/html;charset=utf-8");
		
		String dept_id = request.getParameter("dept_id");
		int id = Integer.parseInt(dept_id);
		DepartmentsDao dao = DepartmentsFactory.getInstance();
		
		String path = "QueryDeptServlet";
		String msg = "删除成功！";
		try {
			int row = dao.delDept(id);
			if(row>0){
				
			}else{
				msg = "删除失败！";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("mesDept", msg);
		
		request.getRequestDispatcher(path)
		.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
