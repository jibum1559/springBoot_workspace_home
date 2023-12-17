package com.kh.springdb.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.Comments;
import com.kh.springdb.model.Product;

public interface CommentRepository extends JpaRepository<Comments, Long> {
	
	List<Comments> findAllByProductOrderByCommentCreateDateDesc(Product product);
	//Iterable<Comments> findAllByProductOrderByCommentCreateDateDesc(Product product);
	//뭐가 문제야
}