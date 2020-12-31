package com.newlecture.web.controller.admin.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Restful API
// Controller : 사용자에게 페이지 전달용(View 사용)
// jsp를 return해야함

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeController {
	
	// /admin/board/notice/list 이렇게 하지 않아도 됨
	@RequestMapping("/list")
	public String list() {
		return "list.jsp";
	}

	@RequestMapping("/reg")
	public String reg() {
		return "reg";
	}

	@RequestMapping("/edit")
	public String edit() {
		return "edit";
	}

}
