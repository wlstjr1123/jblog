package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		
		return count == 1; 
	}
	
	public boolean isId(String id) {
		UserVo check = sqlSession.selectOne("user.idCheck", id);
		return check != null;
	}
	
	public UserVo getUser(UserVo vo) {
		return sqlSession.selectOne("user.findByIdAndPassword", vo);
	}
}
