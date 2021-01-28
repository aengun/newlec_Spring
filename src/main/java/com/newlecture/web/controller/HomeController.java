package com.newlecture.web.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class HomeController {
	
//	@RequestMapping("/admin/board/notice/list")
//	public String adminBoardNoticeList() {
//		return "list";
//	} // 방정리를 위해 분리 >> admin/board : 패키지 / notice : 클래스 / list : 함수
	
//	@RequestMapping("/customer/notice/list")
//	public String list() {
//		return "list";
//	} >> customer로 자리 옮김
	
	@PostMapping("upload")
	@ResponseBody // restcontroller가 아니라 controller에서 view가 아닌, 특정 함수(rest api)를 위한 어노테이션
	public String upload(MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
		
		String url = "/upload"; //저장경로 : 루트 > upload
		//실제 저장경로 확인
		String realPath = request.getServletContext().getRealPath(url);
		System.out.println(realPath);
		
		System.out.println("file uploaded");
		System.out.println(file.getOriginalFilename());
		
		File realPathFile = new File(realPath);
		if(!realPathFile.exists())
			realPathFile.mkdirs();
		
		String uploadedFilePath = realPath + File.separator + file.getOriginalFilename();
		File uploadedFile = new File(uploadedFilePath);
		
		file.transferTo(uploadedFile);
		
		return "ok";
	}
	
	@RequestMapping("/index")
	public String index(Principal principal) {
		
		if(principal != null)
			principal.getName();
		
		return "home.index";
	}
	
	@RequestMapping("/aaa")
	public String aaa() {
		return "aaa";
	}
	
	@RequestMapping("/bbb")
	public String bbb() {
		return "bbb";
	}

}
