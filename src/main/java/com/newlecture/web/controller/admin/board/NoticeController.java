package com.newlecture.web.controller.admin.board;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
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
	@RequestMapping("list")
	public String list(Model model) {

		List<NoticeView> list = service.getViewList(1, 10, "title", "");
		model.addAttribute("list", list);

		return "admin.board.notice.list";
	}

	@RequestMapping("{id}")
	public String detail(@PathVariable("id") int id, Model model) {

		Notice notice = service.get(id);
		Notice prev = service.getPrev(id);
		Notice next = service.getNext(id);

		model.addAttribute("n", notice);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);

		return "admin.board.notice.detail";
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

		// 작성자명은 따로 해야함
//		notice.setWriterId("newlec");
		String uid = principal.getName(); // 인증할 때 사용한 사용자
		notice.setWriterId(uid);

		service.insert(notice);
//		System.out.println("title : " + title + " / content : " + content);
		return "redirect:list";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {

		Notice notice = service.get(id);
		model.addAttribute("n", notice);

		return "admin.board.notice.edit";
	}

	@PostMapping("{id}/edit")
	public String edit(Notice notice) {

		// 새로운 데이터 가져오기
		int id = notice.getId();
		String title = notice.getTitle();
		String content = notice.getContent();

		// 기존 칼럼을 가져와 값 덮어쓰기
		Notice origin = service.get(notice.getId());
		origin.setTitle(title);
		origin.setContent(content);

		service.update(origin);

		return "redirect:../" + notice.getId(); // notice/123/edit => notice/123
	}
	
	@GetMapping("{id}/del")
	public String del(@PathVariable("id") int id) {
		System.out.println(id);
		service.delete(id);

		return "redirect:../list";
	}
	
	@PostMapping("aa")
	public String aa(String action, int[] del) {
		
		if(action.equals("일괄삭제"))
			service.deleteAll(del);
		
		return "redirect:list";
	}
	

}
