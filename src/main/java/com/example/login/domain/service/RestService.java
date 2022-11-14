package com.example.login.domain.service;

import java.util.List;

import com.example.login.domain.model.User;

public interface RestService {
	
	/**
	 * 1件登録用
	 * 
	 * @param user
	 * @return
	 */
	public boolean insert(User user);

	/**
	 * 1件検索用
	 * 
	 * @param userId
	 * @return
	 */
	public User selectOne(String userId);
	
	/**
	 * 全件検索用
	 * 
	 * @return
	 */
	public List<User> selectMany();
	
	/**
	 * 1件更新用
	 * 
	 * @param user
	 * @return
	 */
	public boolean update(User user);
	
	/**
	 * 1件削除用
	 * 
	 * @param userId
	 * @return
	 */
	public boolean delete(String userId);
}
