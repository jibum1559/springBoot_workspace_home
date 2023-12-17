package com.kh.springdb.model;

import jakarta.validation.constraints.*;
import lombok.*;

//사용자 ID나 비밀번호 이메일을 회원가입 할 때 필수로 넣어줬는지 확인
@Getter
@Setter
public class UserCreateForm {
	
	@NotNull(message = "가입자 선택은 필수입니다.")
	private UserRole isRole;
	
	@Size(min = 3, max = 25)
	@NotEmpty(message = "사용자ID는 필수로 기입합니다.")
	private String username;
	
	@NotEmpty(message = "비밀번호는 필수입니다.")
	private String password1;
	
	@NotEmpty(message = "비밀번호 확인은 필수입니다.")
	private String password2;
	
	@NotEmpty(message = "이메일은 필수입니다.")
	@Email
	private String email;
}

/*
 @NotNull : 만약에 넣어준 값이 null 값이라면 메세지가 나올 수 있도록 표기
 -> null 체크여부
 @NotEmpty : 메세지를 예외값으로 발생시킴
 -> Empty 예외체크
 */