package com.icss.system;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/*@WebFilter("/*")*/
public class DBConnFilter implements Filter {

    public DBConnFilter() {
        
    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			//继续发送请求响应
			chain.doFilter(request, response);
		}finally{
			//因为finally是在所有请求响应完整最后执行

		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
