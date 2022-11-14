package com.example.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.login.domain.model.User;
import com.example.login.domain.repository.UserDao;

@Repository("UserDaoNamedJdbcImpl")
public class UserDaoNamedJdbcImpl implements UserDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	/**
	 * Userテーブルの件数を取得
	 * 
	 */
	@Override
	public int count() throws DataAccessException {
		
		String sql = "SELECT COUNT(*) FROM m_user";
		
		// パラメーター生成
		SqlParameterSource params = new MapSqlParameterSource();
		
		// 全件取得してカウント
		return jdbc.queryForObject(sql, params, Integer.class);
	}

	/**
	 * Userテーブルにデータを1件insert
	 * 
	 */
	@Override
	public int insertOne(User user) throws DataAccessException {
		
		String sql = "INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)"
				+ " VALUES (:userId, :password, :userName, :birthday, :age, :marriage, :role)";
		
		// パラメーターの設定
		SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		// SQL実行
		return jdbc.update(sql, params);
	}

	/**
	 * Userテーブルのデータを1件取得
	 * 
	 */
	@Override
	public User selectOne(String userId) throws DataAccessException {
		
		String sql = "SELECT user_id, password, user_name, birthday, age, marriage, role FROM m_user WHERE user_id = :userId";
		
		// パラメーターの設定
		SqlParameterSource params = new MapSqlParameterSource().addValue("userId", userId);
		
		// RowMapper
		RowMapper<User> rowMapper = new UserRowMapper();
		
		// SQL実行
		return jdbc.queryForObject(sql, params, rowMapper);
	}

	/**
	 * Userテーブルの前データを取得
	 * 
	 */
	@Override
	public List<User> selectMany() throws DataAccessException {
		
		String sql = "SELECT user_id, password, user_name, birthday, age, marriage, role FROM m_user";
		
		// パラメーターの設定
		SqlParameterSource params = new MapSqlParameterSource();
		
		// ResultSetExtractorの生成
		UserResultSetExtractor extractor = new UserResultSetExtractor();
		
		// SQL実行
		return jdbc.query(sql, params, extractor);
	}

	/**
	 * Userテーブルを1件更新
	 * 
	 */
	@Override
	public int updateOne(User user) throws DataAccessException {
		
		String sql = "UPDATE m_user SET password = :password, user_name = :userName, birthday = :birthday, age = :age, marriage = :marriage"
				+ " WHERE user_id = :userId";
		
		// パラメーターの設定
		SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		// SQL実行
		return jdbc.update(sql, params);
	}

	/**
	 * Userテーブルを1件削除
	 * 
	 */
	@Override
	public int deleteOne(String userId) throws DataAccessException {
		
		String sql = "DELETE FROM m_user WHERE user_id = :userId";
		
		// パラメーターの設定
		SqlParameterSource params = new MapSqlParameterSource().addValue("userId", userId);
		
		// SQL実行
		int rowNumber = jdbc.update(sql, params);
		
		return rowNumber;
	}

	/**
	 * SQL取得結果をサーバーにCSVで保存する
	 * 
	 */
	@Override
	public void userCsvOut() throws DataAccessException {
		
		String sql = "SELECT user_id, password, user_name, birthday, age, marriage, role FROM m_user";
		
		// ResultSetExtractorの生成
		UserRowCallbackHandler handler = new UserRowCallbackHandler();
		
		// SQL実行&CSV出力
		jdbc.query(sql, handler);
	}

}
