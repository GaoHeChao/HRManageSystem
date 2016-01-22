package com.icss.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.bean.EmpsBean;
import com.icss.dao.EmpsDao;
import com.icss.dao.EmpsFactory;

@WebServlet("/UpdateEmpServlet")
public class UpdateEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateEmpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String name = request.getParameter("empname");
		
		String email = request.getParameter("email");
		String phone_number = request.getParameter("phone_number");
		String job_id = request.getParameter("job_id");
		String salarys = request.getParameter("salary");
		int salary = Integer.parseInt(salarys);
		String department_ids = request.getParameter("department_id");
		int department_id = Integer.parseInt(department_ids);
		
		String path = "QueryEmpsServlet";
		
		String hire_date = request.getParameter("hire_date");
		try {
			System.out.println(hire_date);
			EmpsDao dao = EmpsFactory.getInstance();
			EmpsBean bean = new EmpsBean(name,email,phone_number,hire_date,job_id,salary,department_id);
			int rows = dao.addEmp(bean);
			if(rows>0){
				System.out.println("修改成功");
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
