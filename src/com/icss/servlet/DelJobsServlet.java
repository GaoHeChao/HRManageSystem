package com.icss.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.dao.JobsDao;
import com.icss.dao.JobsFactory;

@WebServlet("/DelJobsServlet")
public class DelJobsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public DelJobsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String job_id = request.getParameter("job_id");
		String path = "QueryDeptServlet";
		JobsDao dao = JobsFactory.getInstance();
		try {
			int t = dao.delJob(job_id);
			if(t>0){
				System.out.println("删除成功");
			}else{
				System.out.println("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(path)
		.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		doGet(request, response);
	}

}
