package com.example.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.login.domain.model.User;

public interface UserDao {

	/**
	 * Userテーブルの件数を取得
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public int count() throws DataAccessException;
	
	/**
	 * Userテーブルにデータを1件insert
	 * 
	 * @param user
	 * @return
	 * @throws DataAccessException
	 */
	public int insertOne(User user) throws DataAccessException;
	
	/**
	 * Userテーブルのデータを1件取得
	 * 
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 */
	public User selectOne(String userId) throws DataAccessException;
	
	/**
	 * Userテーブルの全データを取得
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<User> selectMany() throws DataAccessException;
	
	/**
	 * Userテーブルを1件更新
	 * 
	 * @param user
	 * @return
	 * @throws DataAccessException
	 */
	public int updateOne(User user) throws DataAccessException;
	
	/**
	 * Userテーブルを1件削除
	 * 
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 */
	public int deleteOne(String userId) throws DataAccessException;
	
	/**
	 * SQL取得結果をサーバーにCSVで保存する
	 * 
	 * @throws DataAccessException
	 */
	public void userCsvOut() throws DataAccessException;
}
