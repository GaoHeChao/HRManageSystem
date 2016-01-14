package com.icss.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.dao.DepartmentsDao;
import com.icss.dao.DepartmentsFactory;

@WebServlet("/QueryDeptOnPageServlet")
public class QueryDeptOnPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueryDeptOnPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		DepartmentsDao dao = DepartmentsFactory.getInstance();
		int nowPage = 1;
		
		String nowPagestr = request.getParameter("pagenum");   
		String path="admin/DeptViewServlet.jsp";
		
		List<Map<String, Object>> allData = new ArrayList<>();
		
		if(nowPagestr == null || nowPagestr == ""){
			System.out.println("传入值为空");
		}else{
			nowPage = Integer.parseInt(nowPagestr);
		}
		
		try {
			allData = dao.queryDeptOnPage(nowPage,4);
			
			request.setAttribute("Data", allData);
			request.setAttribute("nowPage", dao.getNowPage());
			request.setAttribute("pageCount", dao.getPageCount());
			request.setAttribute("rowCount", dao.getRowCount());
			System.out.println(nowPage);
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
