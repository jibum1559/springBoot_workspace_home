package com.kh.springdb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.SiteUser;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
	
	//로그인을 하기 위해 검색하는 코드를 작성해줄 예정
	Optional<SiteUser> findByusername(String username);
}