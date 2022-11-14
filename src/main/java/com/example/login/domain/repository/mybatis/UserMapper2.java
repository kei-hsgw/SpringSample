package com.example.login.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.login.domain.model.User;

@Mapper
public interface UserMapper2 {

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
	public boolean updateOne(User user);
	
	/**
	 * 1件削除用
	 * 
	 * @param usetId
	 * @return
	 */
	public boolean deleteOne(String usetId);
}
