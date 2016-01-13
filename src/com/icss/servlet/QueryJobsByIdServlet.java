package com.icss.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.bean.JobsBean;
import com.icss.dao.JobsDao;
import com.icss.dao.JobsFactory;

@WebServlet("/QueryJobsByIdServlet")
public class QueryJobsByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueryJobsByIdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("job_id");
		
		JobsDao dao = JobsFactory.getInstance();
		ResultSet rs = null;
		JobsBean bean = new JobsBean();
		try {
			rs = dao.queryJobsById(id);
			while (rs.next()) {
				bean.setJob_id(rs.getString(1));
				bean.setJob_title(rs.getString(2));
				bean.setMin_salary(rs.getDouble(3));
				bean.setMax_salary(rs.getDouble(4));	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("jbean", bean);
		request.getRequestDispatcher("admin/UpdateJobs.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
