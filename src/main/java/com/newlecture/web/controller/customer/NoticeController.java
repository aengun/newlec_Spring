package com.newlecture.web.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	
	//NoticeService service = new NoticeService();
	//service를 보따리에 담기
	@Autowired
	private NoticeService service; //NoticeService는 @Service로
	

	@RequestMapping("list")
	public String list(Model model) {  

		List<Notice> list = service.getList(1, 10, "title", "");
		
		model.addAttribute("list", list);

		return "customer.notice.list";
	}

	@RequestMapping("detail")
	public String detail(Model model) {
		return "customer.notice.detail";
	}

}
