package com.newlecture.web.controller.admin.board;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

// Restful API
// Controller : 사용자에게 페이지 전달용(View 사용)
// jsp를 return해야함

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeController {
	
	@Autowired
	private NoticeService service;

	// /admin/board/notice/list 이렇게 하지 않아도 됨
	@RequestMapping("/list")
	public String list() {
		return "admin.board.notice.list";
	}

	// get요청
//	@RequestMapping(name = "reg", method = RequestMethod.GET)
	@GetMapping("reg")
	public String reg() {
		System.out.println("get reg");
		return "admin.board.notice.reg";
	}

	// post요청
//	@RequestMapping(name = "reg", method = RequestMethod.POST)
	@PostMapping("reg")
	public String reg(Notice notice, Principal principal) {
		
		//작성자명은 따로 해야함
//		notice.setWriterId("newlec");
		String uid = principal.getName(); //인증할 때 사용한 사용자
		notice.setWriterId(uid);
		
		service.insert(notice);
//		System.out.println("title : " + title + " / content : " + content);
		return "redirect:list";
	}

	@RequestMapping("/edit")
	public String edit() {
		return "admin.board.notice.edit";
	}

}
