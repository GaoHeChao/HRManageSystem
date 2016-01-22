package com.icss.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.icss.dao.PhotoDao;

@WebServlet("/UploadPhotoServlet")
public class UploadPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UploadPhotoServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("emp_id");
		
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");

		File tempFile = new File(tempPath);   //文件上传后的临时目录
		if(!tempFile.exists()){
			tempFile.mkdir();
		}
		
		String message="";
		String path = "QueryPhotosServlet";
		try{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024*1024);
			factory.setRepository(tempFile);   //上传的临时文件保存在tempFile目录下
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			upload.setHeaderEncoding("utf-8");
			if(!upload.isMultipartContent(request)){   //判断是否是上传表单的数据
				return;
			}
			upload.setFileSizeMax(1024*1024);
			upload.setSizeMax(1024*1024*50);
			
			List<FileItem> lists = upload.parseRequest(request);
			System.out.println(lists);
			for(FileItem item : lists){
				if(item.isFormField()){
					String name = item.getFieldName();
					
					
				}else{
					String filename = item.getName();
					System.out.println(filename);
					
					if(filename == null || filename.trim().equals("")){
						continue;
					}
					
					filename = new Date().getTime()+ "_" + filename;
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
					byte buffer[] = new byte[1024];
					int len = 0;
					
					while((len=in.read(buffer))>0){
						out.write(buffer,0,len);
					}
					in.close();
					out.close();
					
					item.delete();          //删除上传时生成的临时文件
					message = "文件上传成功";
					PhotoDao dao = new PhotoDao();
					int employee_id = Integer.parseInt(id);
					dao.addPhoto(savePath,employee_id);
				}
			}
		}catch(Exception e){
			message = "文件上传失败";
			path = "admin/error.jsp";
		}finally{
			request.setAttribute("msg", message);	
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
