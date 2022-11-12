package com.example.login.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.domain.model.User;
import com.example.login.domain.repository.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao dao;
	
	public boolean insert(User user) {
		
		// insert実行
		int rowNumber = dao.insertOne(user);
		
		// 判定用変数
		boolean result = false;
		
		if (rowNumber > 0) {
			result = true;
		}
		
		return result;
	}
}
