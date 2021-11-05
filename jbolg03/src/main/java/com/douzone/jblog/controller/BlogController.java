package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	BlogService blogService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	PostService postService;
	
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

		UserVo userVo = new UserVo();
		userVo.setId(id);
		List<CategoryVo> resultCategoryVo = categoryService.getCategory(userVo);
		model.addAttribute("categoryVo", resultCategoryVo);
		
		if (resultCategoryVo == null || resultCategoryVo.size() == 0) {
			return "/blog/blog-main";
		}
		
//		category.orElse(resultCategoryVo.get(0).getNo() + "");
//		
//		PostVo selectPostVo = new PostVo();
//		selectPostVo.setCategoryNo(Long.parseLong(category.get()));
//		List<PostVo> postVoList = postService.select(selectPostVo);
//		model.addAttribute("postList", postVoList);
//		
//		if (postVoList == null || postVoList.size() == 0) {
//			return "/blog/blog-main";
//		}
//		
//		item.orElse(postVoList.get(0).getNo() + "");
		
		return "/blog/blog-main";
	}
	
	@RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
	public String adminBasic() {
		return "/blog/blog-admin-basic";
	}

	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String adminBasic(@AuthUser UserVo authUser, 
			@RequestParam("file") MultipartFile file,
			@RequestParam(value="title", required = true, defaultValue = "") String title) {
		
		BlogVo vo = new BlogVo();
		vo.setId(authUser.getId());
		vo.setTitle(title);
		
		blogService.updateBlogContent(vo, file);
		return "redirect:/blog/" + authUser.getId();
	}
	
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String adminCategory(@AuthUser UserVo authUser, Model model) {
		
		List<CategoryVo> vo = categoryService.getCategory(authUser);
		model.addAttribute("categoryVo", vo);
		
		
		return "/blog/blog-admin-category";
	}
	
	@RequestMapping(value = "/admin/category/delete/{name}", method = RequestMethod.GET)
	public String adminCategoryDelete(@AuthUser UserVo authUser,
			@PathVariable("name") String name) {
		
		CategoryVo vo = new CategoryVo();
		vo.setBlogId(authUser.getId());
		vo.setName(name);
		
		CategoryVo categoryNoVo = categoryService.findNoByIdAndName(vo);
		postService.delete(categoryNoVo.getNo());
		categoryService.delete(vo);
		return "redirect:/blog/admin/category";
	}
	
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String adminWrite(@AuthUser UserVo authUser, Model model) {
		List<CategoryVo> vo = categoryService.getCategory(authUser);
		model.addAttribute("categoryVo", vo);
		
		return "/blog/blog-admin-write";
	}
	
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String adminWrite(@AuthUser UserVo authUser,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("category") String category) {
		
		PostVo vo = new PostVo();
		vo.setCategoryNo(Long.parseLong(category));
		vo.setContents(content);
		vo.setTitle(title);
		
		postService.insert(vo);
		
		return "redirect:/blog/" + authUser.getId();
	}
}
