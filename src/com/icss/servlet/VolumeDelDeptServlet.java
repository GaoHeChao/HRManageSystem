package com.icss.servlet;

import java.io.IOException;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.dao.DepartmentsDao;
import com.icss.dao.DepartmentsFactory;

@WebServlet("/VolumeDelDeptServlet")
public class VolumeDelDeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public VolumeDelDeptServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String[] paramsList = request.getParameterValues("delList");
		
		DepartmentsDao dao = DepartmentsFactory.getInstance();
		String path = "QueryDeptServlet";
		try {
			if(dao.delDeptByBatch(paramsList)){
				System.out.println("批量删除成功");
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
