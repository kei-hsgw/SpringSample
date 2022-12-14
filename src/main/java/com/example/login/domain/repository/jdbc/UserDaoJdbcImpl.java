package com.example.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.login.domain.model.User;
import com.example.login.domain.repository.UserDao;

@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao {
	
	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * Userテーブルの件数を取得
	 */
	@Override
	public int count() throws DataAccessException {
		int count = jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
		return count;
	}

	/**
	 * Userテーブルにデータを1件insert
	 */
	@Override
	public int insertOne(User user) throws DataAccessException {
		
		// パスワード暗号化
		String password = passwordEncoder.encode(user.getPassword());
		
		int rowNumber = jdbc.update("INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)"
				, user.getUserId(), password, user.getUserName(), user.getBirthday(), user.getAge(), user.isMarriage(), user.getRole());
		return rowNumber;
	}

	/**
	 * Userテーブルのデータを1件取得
	 */
	@Override
	public User selectOne(String userId) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap("SELECT user_id, password, user_name, birthday, age, marriage, role FROM m_user WHERE user_id = ?", userId);
		
		// 結果返却用の変数
		User user = new User();
		
		// 取得したデータを結果返却用の変数に格納
		user.setUserId((String) map.get("user_id"));
		user.setPassword((String) map.get("password"));
		user.setUserName((String) map.get("user_name"));
		user.setBirthday((Date) map.get("birthday"));
		user.setAge((int) map.get("age"));
		user.setMarriage((boolean) map.get("marriage"));
		user.setRole((String) map.get("role"));
		
		return user;
	}

	/**
	 * Userテーブルの全データを取得
	 */
	@Override
	public List<User> selectMany() throws DataAccessException {
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT user_id, password, user_name, birthday, age, marriage, role FROM m_user");
		
		// 結果返却用の変数
		List<User> userList = new ArrayList<>();
		
		// 取得したデータを結果返却用のListに格納
		for (Map<String, Object> map : getList) {
			User user = new User();
			user.setUserId((String) map.get("user_id"));
			user.setPassword((String) map.get("password"));
			user.setUserName((String) map.get("user_name"));
			user.setBirthday((Date) map.get("birthday"));
			user.setAge((int) map.get("age"));
			user.setMarriage((boolean) map.get("marriage"));
			user.setRole((String) map.get("role"));
			userList.add(user);
		}
		return userList;
	}

	/**
	 * Userテーブルを1件更新
	 */
	@Override
	public int updateOne(User user) throws DataAccessException {
		
		// パスワード暗号化
		String password = passwordEncoder.encode(user.getPassword());
		
		int rowNumber = jdbc.update("UPDATE m_user"
				+ " SET password = ?, user_name = ?, birthday = ?, age = ?, marriage = ? WHERE user_id = ?"
				, password, user.getUserName(), user.getBirthday(), user.getAge(), user.isMarriage(), user.getUserId());
		
//		// トランザクション確認のため、わざと例外をthrowする
//		if (rowNumber > 0) {
//			throw new DataAccessException("トランザクションテスト") {
//			};
//		}
		
		return rowNumber;
	}

	/**
	 * Userテーブルを1件削除
	 */
	@Override
	public int deleteOne(String userId) throws DataAccessException {
		int rowNumber = jdbc.update("DELETE FROM m_user WHERE user_id = ?", userId);
		
		return rowNumber;
	}

	/**
	 * Userテーブルのっ全データをCSVに出力する
	 */
	@Override
	public void userCsvOut() throws DataAccessException {
		
		String sql = "SELECT user_id, password, user_name, birthday, age, marriage, role FROM m_user";
		
		// ResultSetExtractorの生成
		UserRowCallbackHandler handler = new UserRowCallbackHandler();
		
		// SQL実行
		jdbc.query(sql, handler);
	}

}
