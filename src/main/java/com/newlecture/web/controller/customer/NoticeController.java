package com.newlecture.web.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

// @Controller 대신 @Component로 써도 된다!
@Controller
@RequestMapping("/customer/notice/")
public class NoticeController {

//	@RequestMapping("/list") // 요청 : /customer/notice/list
//	public String list() {
////		return "/WEB-INF/view/customer/notice/list.jsp";
////		다음과 같이 만드려면 : application.poroperties에 설정
////		return "customer/notice/list";
////		tiles가 인식할 수 있는 이름으로 바꾸기
//		return "customer.notice.list";
//	}
//	
//	@RequestMapping("/detail") 
//	public String detail() {
////		return "/WEB-INF/view/customer/notice/detail.jsp";
////		return "customer/notice/detail";
//		return "customer.notice.detail";
//	}

	// ======================================201231 list를 전달하려면

	// NoticeService service = new NoticeService();

	// service를 Ioc Container에 담기 >> Ioc Container에서 NoticeService를 참조할 수 있는 것이 있으면
	// 알아서 연결
	// setter함수 사용도 안함, 생성자도 필요없이 알아서 연결됨. >> @Component를 가져옴
	@Autowired
	private NoticeService service; // NoticeService는 @Service로

	@RequestMapping("list")
	public String list(Model model) {

		List<Notice> list = service.getList(1, 10, "title", "");

		model.addAttribute("list", list);

		return "customer.notice.list";
	}
	
	// ==============================================================

	// 1. /customer/notice/detail?id=3
	// 2. /customer/notice/3
	// : customer의 notice에 있는 3번 게시글
	
	//1번 방법
//	@RequestMapping("detail")
//	public String detail(Model model, HttpServletRequest request) {
//
//		String id_ = request.getParameter("id");
//
//		Notice notice = service.get(0);
//		model.addAttribute("notice", notice);
//
//		return "customer.notice.detail";
//	}
	
	//2번 방법 : id를 자동으로 가져옴
	@RequestMapping("{id}")
	public String detail(Model model, @PathVariable String id) {

		System.out.println(id);
//		String id_ = request.getParameter("id");

		Notice notice = service.get(Integer.parseInt(id));
		model.addAttribute("notice", notice);

		return "customer.notice.detail";
	}

}
