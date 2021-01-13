package com.newlecture.web.controller;

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
	@ResponseBody
	public String upload(MultipartFile file) {
		
		System.out.println("file uploaded");
		return "ok";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "hello";
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
