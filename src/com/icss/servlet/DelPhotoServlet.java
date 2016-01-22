package com.icss.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.dao.PhotoDao;

@WebServlet("/DelPhotoServlet")
public class DelPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DelPhotoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String photo_id = request.getParameter("photo_id");
		int id = Integer.parseInt(photo_id);
		String path = "QueryPhotosServlet";
		
		PhotoDao dao = new PhotoDao();
		try {
			dao.delPhoto(id);
			System.out.println("删除成功");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
