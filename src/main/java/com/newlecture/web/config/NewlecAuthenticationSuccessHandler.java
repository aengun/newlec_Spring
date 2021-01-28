package com.newlecture.web.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

//@Component
//public class NewlecAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
public class NewlecAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//	@Autowired
//	private MemberService memberService;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		HttpSession session = request.getSession();
		System.out.println("successHandler");
		if (session != null) {

			// SecurityContextHolder : 전역객체,, == Principal
			User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			String uid = authUser.getUsername();

			// 세션에 필요한 정보를 담기
			session.setAttribute("email", "newlec@namoolab.com");
			System.out.println("handler...");

			// 사용자 포인트 등을 업데이트
			// memberService.dailyPointUpOfMember();
			// memberService.updateLastLoginTime(..);

			Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

			// 1 : ROLE_ADMIN
			// 2 : ROLE_MEMBER
			// 3 : ROLE_TEACHER

			// Member
			//
			// MemberRole
			//
			// Role

			// MemberRold 테이블
			// memberId, roleId, defaultRole
			// 1 1 0
			// 1 2 1

			// ROLE_ADMIN, ROLE_TEACHER, ROLE_MEMBER

			// /member/index, /admin/index, /teacher/index

			SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");

			if (savedRequest != null) {
				String returnURL = savedRequest.getRedirectUrl();
				redirectStrategy.sendRedirect(request, response, returnURL);
			} else if (authorities.contains("ROLE_ADMIN")) { // ADMIN인 경우
				redirectStrategy.sendRedirect(request, response, "/admin/index");
			} else if (authorities.contains("ROLE_MEMBER")) { // MEMBER인 경우
				redirectStrategy.sendRedirect(request, response, "/member/index");
			} else {// 그 외가 없음
//				throw new IllegalStateException(); // 인터페이스 쓰는 경우
				super.onAuthenticationSuccess(request, response, authentication); // 인터페이스가 아니라 클래스 상속 해야함
			}

			// 역할별로 각자 자기 대시보드로 이동하기
			// admin -> /admin/index
			// member -> /member/index
			// user -> /index
		}

	}

}
