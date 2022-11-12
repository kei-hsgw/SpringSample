package com.example.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.login.domain.model.GroupOrder;
import com.example.login.domain.model.SignupForm;

@Controller
public class SignupController {

	// ラジオボタン実装
	private Map<String, String> radioMarriage;
	
	// ラジオボタンの初期化メソッド
	private Map<String, String> initRadioMarriage() {
		
		Map<String, String> radio = new LinkedHashMap<>();
		
		// 既婚・未婚をMapに格納
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		
		return radio;
	}
	
	@GetMapping("/signup")
	public String getSignup(@ModelAttribute SignupForm signupForm, Model model) {
		
		// ラジオボタンの初期化メソッド呼び出し
		radioMarriage = initRadioMarriage();
		
		// ラジオボタン用のMapをModelに格納
		model.addAttribute("radioMarriage", radioMarriage);
		
		return "login/signup";
	}
	
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute @Validated(GroupOrder.class) SignupForm signupForm, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return getSignup(signupForm, model);
		}
		
		// 確認用
		System.out.println(signupForm);
		
		return "redirect:/login";
	}
}
