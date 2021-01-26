package com.newlecture.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
//	방법1 : 이렇게 하고 NewlecAuthenticationSuccessHandler에 @Component 달기 
//	@Autowired 
//	private NewlecAuthenticationSuccessHandler successHandler;
	
//	방법2 : @Bean 활용
	@Bean // Bean : 호출을 위한 것이 아님. 스프링에게 객체를 선납함.
	public NewlecAuthenticationSuccessHandler successHandler() {
		return new NewlecAuthenticationSuccessHandler();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
		// 경로 설정
		// 누구는 되고 누구는 안 되는지
		
//		http
//			.authorizeRequests()
//				.antMatchers("/", "/customer").permitAll(); // 누구나 접속 가능
		
//		http
//			.authorizeRequests()
//				.antMatchers("/admin/**").hasAnyRole("ADMIN")
//				.antMatchers("/member/**").hasAnyRole("MEMBER")
//				.anyRequest().authenticated(); // 그 외에는 모두 인증해야함
		
		http
		.authorizeRequests()
			.antMatchers("/member/login", "/member/join").permitAll() // 어떤 사용자도 들어갈 수 있는 곳
			.antMatchers("/member/**").hasAnyRole("MEMBER","TEACHER","ADMIN") //.authenticated()
			.antMatchers("/teacher/**").hasAnyRole("TEACHER","ADMIN")
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
			.and()
		.formLogin()
			.loginPage("/member/login")
			.loginProcessingUrl("/member/login")
			.defaultSuccessUrl("/")
			.successHandler(successHandler())
			.and()
		.logout()
			.logoutUrl("/member/logout")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true) // 세션 지우기
			.and()
		.csrf()
			.disable();
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 데이터
//		auth.ldapAuthentication(); // ldab프로토콜을 이용한 사용자 정보 -> 일반적으로 윈도우 서버에서 사용
//		auth.jdbcAuthentication(); // db 이용한 사용자 정보
		
//		auth
//			.inMemoryAuthentication() // 메모리 상에 있는 사용자 정보
//				.withUser("newlec")
//					.password("{noop}111")
//					.roles("ADMIN","MEMBER")
//				.and()
//				.withUser("dragon")
//					.password("111")
//					.roles("MEMBER");
		
		
		//db를 이용한 계정 사용
//		auth
//			.jdbcAuthentication()
//			.dataSource(dataSource)
//			.usersByUsernameQuery("select uid id, pwd password, 1 enabled from Member where uid=?")
//			.authoritiesByUsernameQuery("select uid id, 'ROLE_ADMIN' roleId from Member where uid = ?")
//			.passwordEncoder(new BCryptPasswordEncoder());
		
	      auth
	         .jdbcAuthentication()
	         .dataSource(dataSource)
	         .usersByUsernameQuery("select uid id, pwd password, 1 enabled from Member where uid=?")
	         .authoritiesByUsernameQuery("select m.uid id, r.name roleId "
	               + "from Member m "
	               + "   join MemberRole mr on m.id = mr.memberId "
	               + " join Role r on r.id = mr.roleId where uid=?")
	         .passwordEncoder(new BCryptPasswordEncoder());
		
		// 양방향
		// 원문 -> encoding -> 전달 -> decoding -> 원문
		// 단방향
		// 원문 -> encoding -> 지문,전달 -> decoding(x) -> 원문	
	}

}
