package com.example.login.domain.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {

	/** ユーザーID */
	private String userId;
	/** パスワード */
	private String password;
	/** ユーザー名 */
	private String userName;
	/** 誕生日 */
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;
	/** 年齢 */
	private int age;
	/** 結婚ステータス */
	private boolean marriage;
}
