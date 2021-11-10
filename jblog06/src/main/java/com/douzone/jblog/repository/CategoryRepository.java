package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(CategoryVo vo) {
		int count = sqlSession.insert("category.insert", vo);
		
		return count == 1; 
	}
	
	public List<CategoryVo> getCategory(UserVo vo) {
		List<CategoryVo> result = sqlSession.selectList("category.findById", vo);
		
		return result;
	}
	
	public boolean overlapCheck(CategoryVo vo) {
		int count = sqlSession.selectOne("category.overlapCheck", vo);
		
		return count == 1;
	}
	
	public CategoryVo findNoByIdAndName(CategoryVo vo) {
		return sqlSession.selectOne("category.findNoByIdAndName", vo);
	}
	
	public boolean delete(CategoryVo vo) {
		int count = sqlSession.delete("category.delete", vo);
		
		return count == 1;
	}
}
