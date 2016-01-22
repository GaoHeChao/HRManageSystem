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

@WebServlet("/AddDeptServlet")
public class AddDeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DepartmentsDao dao = DepartmentsFactory.getInstance();    //工厂模式

    public AddDeptServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String dnums = request.getParameter("department_id");
		int dnum = Integer.parseInt(dnums);
		String dname = request.getParameter("department_name");
		String dloc = request.getParameter("location_name");
		
		DepartmentsBean dBean = new DepartmentsBean(dnum,dname,dloc);
		String path = "admin/AddDept.jsp";
		String msg = "添加成功！";
		try {
			int rows = dao.addDept(dBean);
			if(rows>0){
				path = "QueryDeptOnPageServlet";     //成功转向
			}else{
				msg = "添加失败！";	
			}
		} catch (Exception e) {
			msg = "添加失败！";	
			e.printStackTrace();
		}
		request.setAttribute("message", msg);
		request.getRequestDispatcher(path)
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
