package com.icss.servlet;

import java.io.IOException;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.dao.DepartmentsFactory;
import com.icss.dao.JobsFactory;

@WebServlet("/VolumeDelJobServlet")
public class VolumeDelJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public VolumeDelJobServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String[] paramsList = request.getParameterValues("delJobs");
		String path = "QueryJobsServlet";
		
		try {
			if(JobsFactory.getInstance().deljobByBatch(paramsList)){
				System.out.println("删除成功");
			}else{
				path = "error.jsp";
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
