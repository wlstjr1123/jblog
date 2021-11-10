package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public boolean setCategory(CategoryVo vo) {
		return categoryRepository.insert(vo);
	}
	
	public List<CategoryVo> getCategory(UserVo vo) {
		return categoryRepository.getCategory(vo);
	}
	
	public boolean overlapCheck(CategoryVo vo) {
		return categoryRepository.overlapCheck(vo);
	}
	public CategoryVo findNoByIdAndName(CategoryVo vo) {
		return categoryRepository.findNoByIdAndName(vo);
	}
	
	public boolean delete(CategoryVo vo) {
		return categoryRepository.delete(vo);
	}
}
