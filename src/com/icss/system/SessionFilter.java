package com.icss.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*@WebFilter(
		    urlPatterns = { "/*" }
		)*/
public class SessionFilter implements Filter {
    private static final Map<String, Object> urlsafe = new HashMap<>();
    
    public SessionFilter() {
    	
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String uri = httpServletRequest.getRequestURI();
		
		if(this.checkUrl(uri) || checkLogin(httpServletRequest)){
			chain.doFilter(request, response);
		}else{
			((HttpServletResponse)response).sendRedirect(httpServletRequest.getContextPath()+"/login.jsp");
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
		urlsafe.put("", null);
		urlsafe.put("login.jsp", null);
		urlsafe.put("LoginServlet", null);
		urlsafe.put("ImgCodeMakerServlet", null);
		urlsafe.put("css", null);
		urlsafe.put("img", null);
		
	}
	
	private boolean checkUrl(String currentUrl){
		boolean bl = false;
		int index = currentUrl.indexOf("/",1);
		String targetUrl = currentUrl.substring(index+1);
		
		if(urlsafe.containsKey(targetUrl)){
			bl = true;
		}else{
			String targetUrl2 = null;
			int indexend = targetUrl.indexOf("/");
			if(indexend!=-1){
				targetUrl2 = targetUrl.substring(0, indexend);
				if(urlsafe.containsKey(targetUrl2)){
					bl = true;
				}
			}
		}
		return bl;
	}
	
	private boolean checkLogin(HttpServletRequest httpServletRequest){
		Object userinfo = httpServletRequest.getSession().getAttribute("sname");
		
		return userinfo!=null;
	}

}
