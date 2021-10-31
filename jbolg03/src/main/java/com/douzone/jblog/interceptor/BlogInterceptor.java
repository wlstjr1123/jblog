package com.douzone.jblog.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;

public class BlogInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	BlogService blogService;
	@Autowired
	ServletContext servletContext;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String id = request.getParameter("id");
		
		BlogVo vo = new BlogVo();
		vo.setId(id);
		BlogVo result = blogService.getBlogContent(vo);
		
		BlogVo scBlogVo = (BlogVo) servletContext.getAttribute("blogVo");
		
		if (scBlogVo == null || scBlogVo.getId() != id) {
			servletContext.setAttribute("blogVo", result);
		}
		
		
		return true;
	}
}
