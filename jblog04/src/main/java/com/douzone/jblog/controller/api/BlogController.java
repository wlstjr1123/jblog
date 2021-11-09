package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.vo.CategoryVo;

@RestController("blogApiController")
@RequestMapping("/blog/api")
public class BlogController {
	@Autowired
	BlogService blogService;
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/addCategory/{id}/{name}/{desc}")
	public JsonResult addCategory(@PathVariable("id") String id, 
			@PathVariable("name") String name,
			@PathVariable("desc") String desc) {
		
		CategoryVo vo = new CategoryVo();
		vo.setBlogId(id);
		vo.setCount(0);
		vo.setName(name);
		vo.setDesc(desc);
		
		
		System.out.println(vo);
		if (categoryService.overlapCheck(vo)) {
			return JsonResult.fail("이미 존재하는 카테고리 입니다.");
		} else {
			categoryService.setCategory(vo);
			return JsonResult.success(true);
		}
		
	}
}
