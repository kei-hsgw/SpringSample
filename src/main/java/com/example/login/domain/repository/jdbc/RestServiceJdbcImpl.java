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

	@Override
	public boolean insert(User user) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public User selectOne(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<User> selectMany() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean update(User user) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean delete(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
