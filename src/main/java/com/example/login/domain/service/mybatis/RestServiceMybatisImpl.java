package com.example.login.domain.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.login.domain.model.User;
import com.example.login.domain.repository.mybatis.UserMapper;
import com.example.login.domain.repository.mybatis.UserMapper2;
import com.example.login.domain.service.RestService;

@Transactional
@Service("RestServiceMybatisImpl")
public class RestServiceMybatisImpl implements RestService {
	
	@Autowired
	UserMapper2 userMapper;

	/**
	 * insert実行
	 * 
	 */
	@Override
	public boolean insert(User user) {
		return userMapper.insert(user);
	}

	/**
	 * select実行
	 * 
	 */
	@Override
	public User selectOne(String userId) {
		return userMapper.selectOne(userId);
	}

	/**
	 * select実行
	 * 
	 */
	@Override
	public List<User> selectMany() {
		return userMapper.selectMany();
	}

	/**
	 * update実行
	 * 
	 */
	@Override
	public boolean update(User user) {
		return userMapper.updateOne(user);
	}

	/**
	 * delete実行
	 * 
	 */
	@Override
	public boolean delete(String userId) {
		return userMapper.deleteOne(userId);
	}

}
