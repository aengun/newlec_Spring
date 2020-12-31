package com.newlecture.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
