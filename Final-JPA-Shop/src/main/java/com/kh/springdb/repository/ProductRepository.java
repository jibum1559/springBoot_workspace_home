package com.kh.springdb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.Product;


public interface ProductRepository extends JpaRepository <Product, Integer>{
	//상세보기나 수정하기를 위한 메서드
	Product findProductById(int id);
	
	//오후에 작성할 페이지네이션 메서드
	Page<Product> findAll(Pageable pageable);
	
	Page<Product> findAllByOrderByCreateDateDesc(Pageable pageable);
}