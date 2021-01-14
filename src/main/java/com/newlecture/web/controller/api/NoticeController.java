package com.newlecture.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

// RestController : 데이터 반환용(View 사용 X)
// 문자열을 전달해야함

@RestController("apiNoticeController")
@RequestMapping("/api/notice/")
public class NoticeController {

	@Autowired
	private NoticeService service;

	@RequestMapping("/list")
	public List<NoticeView> list(
			@RequestParam(name = "p", defaultValue = "1") int page, // Integer을 쓰면 null 처리 안해도 됨
			@RequestParam(name = "f" /* , required = false */ , defaultValue = "title") String field,
			@RequestParam(name = "q"/* , required = false */ , defaultValue = "") String query) {

		List<NoticeView> list = service.getViewList(page, 10, field, query);

		return list;

	}

}

/*
 * 
 * 12월 28일 수업 Spring Boot 2.x Spring 5.x 최신 라이브러리를 이용하여 웹 개발을 하는 것이 기본 => 톰캣 설정
 * + Servlet/JSP+JSTL+Spring 라이브러리 + Spring dependency ... => 실행환경 + 라이브러리 +
 * 구성하기 설정 => 부트
 * 
 * 1. 프로젝트 만들기 2. 컨트롤러 추가 => URL 매핑하는 기능을 추가 : URL 매핑 엄청 편해짐 => 출력 방법이 엄청 쉬워졌다.
 * (1) 매핑 (2) 출력 (3) 입력
 * 
 */