package com.icss.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.bean.JobsBean;
import com.icss.dao.JobsDao;
import com.icss.dao.JobsDaoImpl;
import com.icss.dao.JobsFactory;

@WebServlet("/QueryJobsServlet")
public class QueryJobsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueryJobsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		JobsDao dao = JobsFactory.getInstance();
		List<JobsBean> allBeans = new ArrayList<>();
		try {
			allBeans = dao.queryAllJobs();		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("allJobs",allBeans);
		request.getRequestDispatcher("/admin/JobsViewServlet.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
