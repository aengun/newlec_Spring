package com.newlecture.web.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class NewlecAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

//	@Autowired
//	private MemberService memberService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		System.out.println("successHandler");
		if(session != null) {
			
			// SecurityContextHolder : 전역객체,, == Principal
			User authUser = (User) SecurityContextHolder
							.getContext()
							.getAuthentication()
							.getPrincipal();
			
			String uid = authUser.getUsername();
			
			// 세션에 필요한 정보를 담기
			session.setAttribute("email", "newlec@namoolab.com");
			System.out.println("handler...");
			
			// 사용자 포인트 등을 업데이트
			// memberService.dailyPointUpOfMember();
			// memberService.updateLastLoginTime(..);
			
			// 역할별로 각자 자기 대시보드로 이동하기
			// admin -> /admin/index
			// member -> /member/index
			// user -> /index
		}
		
	}

}
