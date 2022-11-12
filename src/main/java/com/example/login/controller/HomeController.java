package com.example.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.login.domain.model.User;
import com.example.login.domain.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userservice;
	
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
	
	@PostMapping("/logout")
	public String postLogout() {
		return "redirect:/login";
	}
	
	@GetMapping("/userList/csv")
	public String getUserListCsv(Model model) {
		return getUserList(model);
	}
}
