package com.douzone.jblog.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

public class BlogAdminInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	BlogService blogService;
	@Autowired
	ServletContext servletContext;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		
		if (userVo == null) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		
		return true;
	}
}
