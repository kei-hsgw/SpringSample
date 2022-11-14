package com.example.login.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.login.domain.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService service;

	@Test
	@DisplayName("ユーザーリスト画面のユーザー件数が10のテスト")
	@WithMockUser
	void testGetUserList() throws Exception {
		when(service.count()).thenReturn(10);
		
		mockMvc.perform(get("/userList"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("合計：10件")));
	}

}
