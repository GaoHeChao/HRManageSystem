package com.icss.servlet;

import java.io.IOException;
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
		System.out.println(dnum +"\t"+ dname +"\t"+ dloc);
		DepartmentsBean dBean = new DepartmentsBean(dnum,dname,dloc);
		try {
			dao.addDept(dBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
