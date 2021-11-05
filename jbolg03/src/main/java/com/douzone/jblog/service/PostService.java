package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public boolean delete(Long no) {
		return postRepository.delete(no);
	}
	
	public boolean insert(PostVo vo) {
		return postRepository.insert(vo);
	}
	
	public List<PostVo> select(PostVo vo) {
		return postRepository.select(vo);
	}
}
