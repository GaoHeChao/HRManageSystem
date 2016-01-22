package com.icss.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownPhotoServlet")
public class DownPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DownPhotoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String filename = request.getParameter("photoName");
		String fileName = new String(filename.getBytes("iso8859-1"), "UTF-8");
		
		String fileSaveRootPath = this.getServletContext().getRealPath("/WEB-INF/upload");
		File file = new File(fileSaveRootPath + "\\" + fileName);
		
		if(!file.exists()){
			request.setAttribute("msg", "您要下载的文件已删除");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;    //结束程序
		}
		
		String realName = fileName.substring(fileName.lastIndexOf("_")+1);
		System.out.println(realName);
		response.setHeader("content-disposition", "attachment;filename=" + 
                URLEncoder.encode(realName, "UTF-8"));
		FileInputStream in = new FileInputStream(fileSaveRootPath+"\\"+ fileName);
		OutputStream out = response.getOutputStream();
		
		byte buffer[] = new byte[1024];
		int len = 0;
		while((len=in.read(buffer))>0){
			out.write(buffer,0,len);
			
		}
		
		in.close();
		out.close();	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
