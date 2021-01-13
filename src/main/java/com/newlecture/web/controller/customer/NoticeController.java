package com.newlecture.web.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
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

	// service를 IoC Container에 담기 >> Ioc Container에서 NoticeService를 참조할 수 있는 것이 있으면
	// 알아서 연결
	// setter함수 사용도 안함, 생성자도 필요없이 알아서 연결됨. >> @Component를 가져옴
	@Autowired // IoC Container에서 NoticeService를 참조하는 것이 있으면 여기에 연결하게됨(setter도 필요없음)
	private NoticeService service; // NoticeService는 @Service로

	@RequestMapping("list")
	public String list(@RequestParam(name = "p", defaultValue = "1") int page // Integer을 쓰면 null 처리 안해도 됨
			, @RequestParam(name = "f" /*, required = false*/ , defaultValue = "title" ) String field,
			@RequestParam(name = "q"/* , required = false */  , defaultValue = "" ) String query, Model model) {

//		List<Notice> list = service.getList(1, 10, "title", "");
		List<NoticeView> list = service.getViewList(page, 10, field, query);
		
		for(NoticeView n : list)
	         n.setTitle(n.getTitle().replace(query, "<span style=\"color:red;\">"+query+"</span>"));
		
		int count = service.getCount(field, query);
		model.addAttribute("list", list);

//		model.addAttribute("count", count);
		// count가 아니라 pageCount를 전달하자!
		// size = 10;
		// pageCount = count(92라고 가정) / size; -> 9page + 2row
		int size = 10;
		int pageCount = (int) Math.ceil(count / (float) size); // -> 9.2
		// Math.ceil()/Math.floor()/Math.round()
		model.addAttribute("pageCount", pageCount);
		return "customer.notice.list";
	}

	// ==============================================================

	// 1. /customer/notice/detail?id=3
	// 2. /customer/notice/3
	// : customer의 notice에 있는 3번 게시글

	// 1번 방법
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

	// 2번 방법 : id를 자동으로 가져옴
	@RequestMapping("{id}")
//	public String detail(Model model, @PathVariable String id) {
	public String detail(@PathVariable("id") Integer id, Model model) {

		Notice notice = service.get(id);
		model.addAttribute("n", notice);

		return "customer.notice.detail";
	}

}
