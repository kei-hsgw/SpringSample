package com.example.login.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.login.domain.model.SignupForm;
import com.example.login.domain.model.User;
import com.example.login.domain.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userservice;
	
	// 結婚ステータス用のラジオボタン窯変
	private Map<String, String> radioMarriage;
	
	// ラジオボタンの初期化メソッド
	private Map<String, String> initRadioMarriage() {
		
		Map<String, String> radio = new LinkedHashMap<>();
		
		// 既婚・未婚をMapに格納
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		
		return radio;
	}
	
	@GetMapping("/home")
	public String getHome(Model model) {
		
		// contents部分にホーム画面を表示するための文字列を登録
		model.addAttribute("contents", "login/home :: home_contents");
		
		return "login/homeLayout";
	}
	
	@GetMapping("/userList")
	public String getUserList(Model model) {
		
		// contents部分にユーザー一覧を表示するための文字列を登録
		model.addAttribute("contents", "login/userList :: userList_contents");
		
		List<User> userList = userservice.selectMany();
		model.addAttribute("userList", userList);
		
		int count = userservice.count();
		model.addAttribute("userListCount", count);
		
		return "login/homeLayout";
	}
	
	@GetMapping("/userDetail/{id:.+}")
	public String getUserDetail(@ModelAttribute SignupForm signupForm, Model model, @PathVariable("id")String userId) {
		
		// ユーザーID確認
		System.out.println("userId = " + userId);
		
		// contents部分にユーザー詳細を表示するための文字列を登録
		model.addAttribute("contents", "login/userDetail :: userDetail_contents");
		
		// 結婚ステータス用ラジオボタンの初期化
		radioMarriage = initRadioMarriage();
		model.addAttribute("radioMarriage", radioMarriage);
		
		// ユーザーIDのチェック
		if (userId != null && userId.length() > 0) {
			User user = userservice.selectOne(userId);
			// 値をコピー(user → signupForm)
			BeanUtils.copyProperties(user, signupForm);
			
			model.addAttribute("signupForm", signupForm);
		}
		return "login/homeLayout";
	}
	
	@PostMapping(value = "/userDetail", params = "update")
	public String postUserDetailUpdate(@ModelAttribute SignupForm signupForm, Model model) {
		
		System.out.println("更新ボタンの処理");
		
		User user = new User();
		// 値をコピー(signupForm → user)
		BeanUtils.copyProperties(signupForm, user);
		
		try {
			// 更新実行
			boolean result = userservice.updateOne(user);
			
			if (result == true) {
				model.addAttribute("result", "更新成功");
			} else {
				model.addAttribute("result", "更新失敗");
			}
		} catch (DataAccessException e) {
			model.addAttribute("result", "更新失敗(トランザクションテスト)");
		}
		
		return getUserList(model);
	}
	
	@PostMapping(value = "/userDetail", params = "delete")
	public String postUserDetailDelete(@ModelAttribute SignupForm signupForm, Model model) {
		
		System.out.println("削除ボタンの処理");
		
		// 削除実行
		boolean result = userservice.deleteOne(signupForm.getUserId());
		
		if (result == true) {
			model.addAttribute("result", "削除成功");
		} else {
			model.addAttribute("result", "削除失敗");
		}
		
		return getUserList(model);
	}
	
	@PostMapping("/logout")
	public String postLogout() {
		return "redirect:/login";
	}
	
	@GetMapping("/userList/csv")
	public ResponseEntity<byte[]> getUserListCsv(Model model) {
		
		// ユーザーを全件取得して、CSVサーバーに保存する
		userservice.userCsvOut();
		
		byte[] bytes = null;
		
		try {
			// サーバーに保存されているsample.csvファイルをbyteで取得
			bytes = userservice.getFile("sample.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// HTTPヘッダーの設定
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/csv; charset=UTF-8");
		header.setContentDispositionFormData("filename", "sample.csv");
		
		// sample.csvを戻す
		return new ResponseEntity<>(bytes, header, HttpStatus.OK);
	}
	
	@GetMapping("/admin")
	public String getAdmin(Model model) {
		
		// contents部分にユーザー詳細を表示するための文字列を登録
		model.addAttribute("contents", "login/admin :: admin_contents");
		
		return "login/homeLayout";
	}
}
