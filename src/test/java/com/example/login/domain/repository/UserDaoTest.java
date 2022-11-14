package com.example.login.domain.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserDaoTest {
	
	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;

	// カウントメソッドのテスト
	@Test
	@DisplayName("count()の結果が2件であることをテスト")
	void countTest1() {
		assertEquals(dao.count(), 2);
	}

}
