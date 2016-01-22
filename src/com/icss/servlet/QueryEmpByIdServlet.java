package com.icss.servlet;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.bean.DepartmentsBean;
import com.icss.bean.EmpsBean;
import com.icss.bean.JobsBean;
import com.icss.dao.DepartmentsDao;
import com.icss.dao.DepartmentsFactory;
import com.icss.dao.EmpsDao;
import com.icss.dao.EmpsFactory;
import com.icss.dao.JobsDao;
import com.icss.dao.JobsFactory;

@WebServlet("/QueryEmpByIdServlet")
public class QueryEmpByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueryEmpByIdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String emp_id = request.getParameter("emp_id");
		int employee_id = Integer.parseInt(emp_id);
		
		EmpsDao dao = EmpsFactory.getInstance();
		ResultSet rs = null;
		EmpsBean eBean = new EmpsBean();
		String path = "QueryEmpsServlet";
		
        DepartmentsDao ddao = DepartmentsFactory.getInstance();
		JobsDao jdao = JobsFactory.getInstance();
		List<DepartmentsBean> dbean = new ArrayList<>();
		List<JobsBean> jbean = new ArrayList<>();

		try {
			dbean = ddao.queryAllData();
			jbean = jdao.queryAllJobs();
			rs = dao.queryEmpById(employee_id);
			if(rs.next()){
				eBean.setEmp_name(rs.getString(2));
				eBean.setEmail(rs.getString(3));
				eBean.setPhone_number(rs.getString(4));
				eBean.setHire_date(rs.getString(5));
				eBean.setJob_id(rs.getString(6));
				eBean.setSalary(rs.getInt(7));
				eBean.setDepartment_id(rs.getInt(8));

				path = "admin/UpdateEmp.jsp";
				
			}
			System.out.println(eBean.getHire_date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("jobbean", jbean);
		request.setAttribute("depbean", dbean);
		request.setAttribute("empBean", eBean);
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
