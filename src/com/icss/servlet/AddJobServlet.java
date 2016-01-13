package com.icss.servlet;

import java.io.IOException;
import java.nio.file.Path;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.bean.JobsBean;
import com.icss.dao.JobsDao;
import com.icss.dao.JobsFactory;


@WebServlet("/AddJobServlet")
public class AddJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddJobServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String job_id = request.getParameter("job_id");
		String job_title = request.getParameter("job_title");
		String min_salary = request.getParameter("min_salary");
		double minsal = Double.parseDouble(min_salary);
		String max_salary = request.getParameter("max_salary");
		double maxsal = Double.parseDouble(max_salary);
		
		JobsDao dao = JobsFactory.getInstance();
		JobsBean bean = new JobsBean(job_id,job_title,minsal,maxsal);
		String path = "QueryJobsServlet";
		try {
			int rows = dao.addJob(bean);
			if(rows>0){
				System.out.println("插入成功！");
			}else{
				path = "admin/AddJobs.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(path)
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
