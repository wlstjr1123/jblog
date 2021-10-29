package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public boolean getIdCheck(String id) {
		
		return userRepository.isId(id);
	}
	
	public UserVo getUser(UserVo vo) {
		return userRepository.getUser(vo);
	}
}
