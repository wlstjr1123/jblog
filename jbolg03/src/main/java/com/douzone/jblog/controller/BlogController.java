package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;

@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	BlogService blogService;
	
	@RequestMapping()
	public String main() {
		
		return "/blog/blog-main";
	}
}
