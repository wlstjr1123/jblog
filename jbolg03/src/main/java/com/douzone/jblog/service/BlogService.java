package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	
	public boolean setBlogContent(BlogVo vo) {
		return blogRepository.insert(vo);
	}
	
	public BlogVo getBlogContent(BlogVo vo) {
		return blogRepository.getBlog(vo);
	}
}
