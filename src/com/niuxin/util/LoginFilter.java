package com.niuxin.util;



import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;

public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request0, ServletResponse response1,
			FilterChain chain ) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)request0;
		HttpServletResponse response =(HttpServletResponse)response1;
		HttpSession session = request.getSession();		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String url = request.getServletPath();
		if(session == null){
			/*		
			 if(url!=null && !"".equals(url) && (!"/login.jsp".equals(url) && !"/user/login_login.do".equals(url))){
				response.sendRedirect(basePath+"login.jsp");
				return;
			}
		*/
		}else{
		/*
			Object user_id = session.getAttribute("user_id");
			if(user_id == null || "".equals(user_id)){
				if(url!=null && !"".equals(url) && (!"/login.jsp".equals(url) && !"/user/login_login.do".equals(url))){
					response.sendRedirect(basePath+"login.jsp");
					return;
				}
			} 
			*/	
		}
		chain.doFilter(request0,response1);
		return;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
}
