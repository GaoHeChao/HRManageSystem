package com.icss.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.bean.DepartmentsBean;
import com.icss.bean.JobsBean;
import com.icss.dao.DepartmentsDao;
import com.icss.dao.DepartmentsFactory;
import com.icss.dao.EmpsDao;
import com.icss.dao.EmpsFactory;
import com.icss.dao.JobsDao;
import com.icss.dao.JobsFactory;

@WebServlet("/QueryEmpDataServlet")
public class QueryEmpDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueryEmpDataServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DepartmentsDao ddao = DepartmentsFactory.getInstance();
		
		JobsDao jdao = JobsFactory.getInstance();
		List<DepartmentsBean> dbean = new ArrayList<>();
		List<JobsBean> jbean = new ArrayList<>();
		try {
			dbean = ddao.queryAllData();
			jbean = jdao.queryAllJobs();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("jobbean", jbean);
		request.setAttribute("depbean", dbean);
		request.getRequestDispatcher("admin/AddEmp.jsp")
		.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
