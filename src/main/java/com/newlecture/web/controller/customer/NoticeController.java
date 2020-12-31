package com.newlecture.web.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/notice/")
public class NoticeController {

	@RequestMapping("/list") // 요청 : /customer/notice/list
	public String list() {
//		return "/WEB-INF/view/customer/notice/list.jsp";
//		다음과 같이 만드려면 : application.poroperties에 설정
//		return "customer/notice/list";
//		tiles가 인식할 수 있는 이름으로 바꾸기
		return "customer.notice.list";
	}
	
	@RequestMapping("/detail") 
	public String detail() {
//		return "/WEB-INF/view/customer/notice/detail.jsp";
//		return "customer/notice/detail";
		return "customer.notice.detail";
	}

}
