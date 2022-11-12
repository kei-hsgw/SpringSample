package com.example.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.domain.model.User;
import com.example.login.domain.repository.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao dao;
	
	/**
	 * insert用
	 * 
	 * @param user
	 * @return
	 */
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
	
	/**
	 * カウント用
	 * 
	 * @return
	 */
	public int count() {
		return dao.count();
	}
	
	/**
	 * 全件取得用
	 * 
	 * @return
	 */
	public List<User> selectMany() {
		return dao.selectMany();
	}
}
