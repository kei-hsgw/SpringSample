package com.example.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.login.domain.model.User;

@Repository("UserDaoJdbcImpl3")
public class UserDaoJdbcImpl3 extends UserDaoJdbcImpl {

	@Autowired
	private JdbcTemplate jdbc;
	
	/**
	 * 1件取得
	 * 
	 */
	@Override
	public User selectOne(String userId) {
		
		String sql = "SELECT user_id, password, user_name, birthday, age, marriage, role FROM m_user WHERE user_id = ?";
		
		// RowMapperの生成
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		
		// SQL実行
		return jdbc.queryForObject(sql, rowMapper, userId);
	}
	
	@Override
	public List<User> selectMany() {
		
		String sql = "SELECT user_id, password, user_name, birthday, age, marriage, role FROM m_user";
		
		// RowMapperの生成
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		
		// SQL実行
		return jdbc.query(sql, rowMapper);
	}
}
