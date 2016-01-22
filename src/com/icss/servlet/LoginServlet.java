package com.icss.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.dao.LoginDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		//将用户名存储到客户端的cookie
		String adminname = request.getParameter("adminname");
		String adminpwd = request.getParameter("adminpwd");
		String adminlevel = request.getParameter("adminlevel");
		String admincode = request.getParameter("admincode");
		String path = "login.jsp";
		
		int dlevel = Integer.parseInt(adminlevel); 
		LoginDao dao = new LoginDao();
		HttpSession session = request.getSession();    
		String randcode = session.getAttribute("randcode").toString();  //验证码
		
		try {
			ResultSet rs = dao.queryUser(adminname, adminpwd, dlevel);  //查询数据库
			
			if(rs != null && rs.next() && admincode.equals(randcode)){  //对ResultSet判空不熟悉
				Cookie name = new Cookie("name", adminname);
				Cookie level = new Cookie("level", adminlevel);
				name.setMaxAge(60*60*24*7);
				level.setMaxAge(60*60*24*7);
				
				response.addCookie(name);
				response.addCookie(level);
				
				session.setAttribute("sname", adminname);
				session.setAttribute("slevel", adminlevel);
				
				path = "admin/default.jsp";
			}else{
				System.out.println("用户不存在！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(path)
		.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
