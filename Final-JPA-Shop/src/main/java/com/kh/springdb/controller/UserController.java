package com.kh.springdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.kh.springdb.model.UserCreateForm;
import com.kh.springdb.model.UserRole;
import com.kh.springdb.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
//공통으로 들어가는 url이 있다면 RequestMapping 사용해서 user로 묶어주기
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	
	//회원가입 창
	@GetMapping("/signup")
	public String signUp(UserCreateForm userCreateForm) {
		return "signup_form";
	}
	
	//회원가입에 대한 postMapping
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		//만약 패스워드 두 개가 일치하지 않는 다면 아래
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			return "signup_form";
		}
		//html 폼에서 선택한 역할을 가지고 오기 위해
		
		UserRole role = userCreateForm.getIsRole();
		userService.createUser(userCreateForm.getUsername(), userCreateForm.getPassword1(), userCreateForm.getEmail(), userCreateForm.getIsRole());

		//userService.createUser(userCreateForm.getUsername(), userCreateForm.getPassword1(), userCreateForm.getEmail());
		
		return "redirect:/";
		
	}
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
}