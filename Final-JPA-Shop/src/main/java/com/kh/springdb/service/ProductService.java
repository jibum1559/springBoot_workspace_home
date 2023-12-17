package com.kh.springdb.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.springdb.model.Product;
import com.kh.springdb.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	
	public List<Product> allProductView() {
		return productRepository.findAll();
	}
	
	// pagination add
		public Page<Product> getList(int page){
		//int conPage = Math.max(0, page);
		//Pageable pageable = PageRequest.of(conPage, 1);// page 페이지 값,  1= 페이지당 보여줄 목록 개수
		Pageable pageable = PageRequest.of(page, 3, Sort.by("createDate").descending());
		return productRepository.findAll(pageable);
		}
	
	public void saveProduct(Product product, MultipartFile imgFile) throws IllegalStateException, IOException { 
		//MultipartFile form에서 등록한걸 가지고옴
		//이미지 파일 이름 가져오기
		String OriginName = imgFile.getOriginalFilename();
		
		String projectpath = System.getProperty("user.dir") + "/src/main/resources/static/img";
		
		File saveFile = new File(projectpath, OriginName);
		//MultipartFile 에 File 로 읽어온 이미지 파일을 저장하기 위해 변환
		//MultipartFile imgFile      imgFile  saveFile
		imgFile.transferTo(saveFile);
		product.setImgName(OriginName);//가져온 파일 이름 원본 저장
		product.setImgPath("/img/" + OriginName);//경로 저장을 DB에 작성해주기
		productRepository.save(product);
		
		}
	
	//상품 상세페이지나 수정하기 위해 아이디를 가져와서 상품을 보여주거나 수정할 수 있게 가져오는 메서드
	public Product getProductById(int id) {
		return productRepository.findProductById(id);
	}
	
	//제품에 좋아요를 받을 수 있도록 서비스 만들어줌
	public void likeProduct(int productId) {
		Product product = productRepository.findById(productId).orElse(null);
		if(product != null) {
			product.setLikes(product.getLikes() + 1);
			productRepository.save(product);
		}
	}
	/*
	public void likeProduct(int productId) {
	    Optional<Product> optionalProduct = productRepository.findById(productId);
	    if (optionalProduct.isPresent()) {
	        Product product = optionalProduct.get();
	        product.setLikes(product.getLikes() + 1);
	        productRepository.save(product);
	    }
	}
	*/
}

/*
 ※ Pageable pageable = PageRequest.of(page, 3, Sort.by("createDate").descending());
 
Pageable: Spring Framework에서 페이징 처리를 위해 사용되는 인터페이스입니다. 페이징 처리에 필요한 정보를 제공하는 메서드를 정의하고 있습니다.
PageRequest: Pageable 인터페이스의 구현체 중 하나로, 페이징 처리에 필요한 정보를 제공합니다. 
PageRequest.of() 메서드를 사용하여 PageRequest 객체를 생성할 수 있습니다.
page: 페이지 번호를 의미합니다. 조회할 페이지의 번호를 지정합니다.
3: 한 페이지에 보여질 항목의 개수를 의미합니다. 여기서는 한 페이지에 3개의 항목이 보여집니다.
Sort: 정렬을 위한 기능을 제공하는 클래스입니다. Sort.by() 메서드를 사용하여 정렬 조건을 생성할 수 있습니다.
"createDate": 정렬 기준이 되는 필드 이름입니다. 여기서는 "createDate" 필드를 기준으로 정렬합니다.
.descending(): 내림차순으로 정렬하는 메서드입니다. 정렬 방식을 지정할 수 있으며, 여기서는 "createDate" 필드를 내림차순으로 정렬합니다.
따라서, 위의 코드는 PageRequest.of() 메서드를 사용하여 Pageable 객체를 생성하고, 페이지 번호, 페이지당 항목 개수, 정렬 조건을 설정한 것입니다. 
이렇게 생성된 Pageable 객체는 데이터 조회 시 페이징 처리를 위해 사용될 수 있습니다.
 */