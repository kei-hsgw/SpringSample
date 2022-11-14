package com.example.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.login.domain.model.User;

@Repository("UserDaoJdbcImpl4")
public class UserDaoJdbcImpl4 extends UserDaoJdbcImpl {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public List<User> selectMany() {
		
		String sql = "SELECT user_id, password, user_name, birthday, age, marriage, role FROM m_user";
		
		// ResultSetExtractorの生成
		UserResultSetExtractor extractor = new UserResultSetExtractor();
		
		// SQL実行
		return jdbc.query(sql, extractor);
	}
}
