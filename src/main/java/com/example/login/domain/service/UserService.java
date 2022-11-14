package com.example.login.domain.service;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.login.domain.model.User;
import com.example.login.domain.repository.UserDao;

@Transactional
@Service
public class UserService {

	@Autowired
	@Qualifier("UserDaoJdbcImpl")  // どのBeanを使用するかを指定する(インターフェースを継承したクラスが複数ある場合)
	UserDao dao;
	
	/**
	 * insert用
	 * 
	 * @param user
	 * @return
	 */
	public boolean insert(User user) {
		
		// insert実行
		int rowNumber = dao.insertOne(user);
		
		// 判定用変数
		boolean result = false;
		
		if (rowNumber > 0) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * カウント用
	 * 
	 * @return
	 */
	public int count() {
		return dao.count();
	}
	
	/**
	 * 全件取得用
	 * 
	 * @return
	 */
	public List<User> selectMany() {
		return dao.selectMany();
	}
	
	/**
	 * 1件取得用
	 * 
	 * @param userId
	 * @return
	 */
	public User selectOne(String userId) {
		return dao.selectOne(userId);
	}
	
	/**
	 * 1件更新用
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateOne(User user) {
		
		// 1件更新
		int rowNumber = dao.updateOne(user);
		
		// 判定用変数
		boolean result = false;
		
		if (rowNumber > 0) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 1件削除
	 * 
	 * @param userId
	 * @return
	 */
	public boolean deleteOne(String userId) {
		
		// 1件削除
		int rowNumber = dao.deleteOne(userId);
		
		// 判定用変数
		boolean result = false;
		
		if (rowNumber > 0) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * CSV出力
	 * 
	 * @throws DataAccessException
	 */
	public void userCsvOut() throws DataAccessException {
		
		// CSV出力
		dao.userCsvOut();
	}
	
	/**
	 * サーバーに保存されているファイルを取得して、byte配列に変換
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public byte[] getFile(String fileName) throws IOException {
		
		// ファイルシステム(デフォルト)の取得
		FileSystem fs = FileSystems.getDefault();
		
		// ファイルの取得
		Path p = fs.getPath(fileName);
		
		// ファイルをbyte配列に変換
		byte[] bytes = Files.readAllBytes(p);
		
		return bytes;
	}
}
