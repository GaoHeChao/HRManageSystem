package com.icss.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.dao.PhotoDao;

@WebServlet("/QueryPhotosServlet")
public class QueryPhotosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueryPhotosServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		Map<String, Object> fileNameMap = new HashMap<>();

		File file = new File(uploadFilePath);
		listfile(file,fileNameMap);

		PhotoDao dao = new PhotoDao();
		ResultSet rs = null;
		String emp_id = request.getParameter("emp_id");
		int id = Integer.parseInt(emp_id);
		
		try {
			rs = dao.queryPhotosById(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("resultset", rs);
		request.setAttribute("fileMap", fileNameMap);
		request.getRequestDispatcher("admin/PhotoViewServlet.jsp").forward(request, response);
		
	}
	
	private void listfile(File file, Map<String, Object> map){
		if(!file.isFile()){
			File files[] = file.listFiles();
			for(File f : files){
				listfile(f, map);
			}
		}else{
			String realName = file.getName().substring(file.getName().indexOf("_")+1);
			String name = file.getName();
			map.put(name, realName);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
