package com.example.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.login.domain.model.User;
import com.example.login.domain.repository.UserDao;
import com.example.login.domain.service.RestService;

@Transactional
@Service
public class RestServiceJdbcImpl implements RestService {
	
	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;

	/**
	 * 1件登録用
	 * 
	 */
	@Override
	public boolean insert(User user) {
		
		int result = dao.insertOne(user);
		
		if (result == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 1件検索用
	 * 
	 */
	@Override
	public User selectOne(String userId) {
		return dao.selectOne(userId);
	}

	/**
	 * 全件検索用
	 * 
	 */
	@Override
	public List<User> selectMany() {
		return dao.selectMany();
	}

	/**
	 * 1件更新用
	 * 
	 */
	@Override
	public boolean update(User user) {
		
		int result = dao.updateOne(user);
		
		if (result == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 1件削除用
	 * 
	 */
	@Override
	public boolean delete(String userId) {
		
		int result = dao.deleteOne(userId);
		
		if (result == 0) {
			return false;
		} else {
			return true;
		}
	}

}
