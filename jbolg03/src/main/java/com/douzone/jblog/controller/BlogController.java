package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;

@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	BlogService blogService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping({ "/{id}", "/{id}/{category}", "/{id}/{catefory}/{item}" })
	public String main(
			@PathVariable("id") String id, 
			@PathVariable("category") Optional<String> category,
			@PathVariable("item") Optional<String> item,
			Model model) {

		BlogVo vo = new BlogVo();
		vo.setId(id);
		BlogVo result = blogService.getBlogContent(vo);
		model.addAttribute("blogVo", result);

		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(id);
		List<CategoryVo> resultCategoryVo = categoryService.getCategory(categoryVo);
		model.addAttribute("categotyVo", resultCategoryVo);
		model.addAttribute("accessBlog", id);
		
//		category.orElseGet(() -> "0");
//		item.orElseGet(() -> "0")
		
		return "/blog/blog-main";
	}
}
