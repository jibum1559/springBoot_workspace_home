package com.kh.springdb.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.SiteUser;
import com.kh.springdb.model.UserRole;
import com.kh.springdb.repository.UserRepository;

import lombok.*;

/*
데이터베이스나 외부에서 로그인하여 인증을 하기 위해서는 인증 처리를 해야함

 UserDetailsService : 사용자 정보를 인증하는 것
*/

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {
	private final UserRepository userRepository;
	
	//유저에 대한 정보를 로그인 할 때 userDetails를 사용해서 로그인할 수 있는 유저가 있는지 확인할 것
	//사용자명을 기준으로 사용자 정보를 가져오게 할 것
	
	public UserDetails loadUserByUsername(String username) {
		Optional<SiteUser> _siteUser = userRepository.findByusername(username);
		if(_siteUser.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}
		SiteUser user = _siteUser.get();
		List<GrantedAuthority> authorities = new ArrayList<>();
		//만약에 admin user로 로그인된다면 로그인 분류를 role에 따라 추가로 작성
		if ("admin".equals(username)) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		return new User(user.getUsername(), user.getPassword(), authorities);
	}
}

/*
※ UserDetails : 
- 스프링 시큐리티가 사용자의 인증과 권한 부여를 처리하는데 필요한 정보를 제공
- 인터페이스로 다양한 종류의 메서드가 있음
- UserDetails 메서드 종류

* getAuthorities() : 
- 사용자가 가지고 있는 권한 목록을 반환
- 권한은 GrantedAuthority 이것으로 가지고옴
- 권한은 정의된 권한에 따라 달라짐. 권한은 개발자가 설정

* getPassword() :
- 사용자의 비밀번호를 반환
- 데이터베이스에서 암호화 처리된 형태로 저장되어 있음

* getUsername() : 사용자명을 반환

* isEnables() : 계정이 활성화 되어있는지 여부를 Boolean 값으로 나타냄

 */