package com.newlecture.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
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
			.antMatchers("/member/**").authenticated()
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
			.and()
		.formLogin()
			.loginPage("/member/login")
			.loginProcessingUrl("/member/login")
			.defaultSuccessUrl("/")
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
		auth
			.inMemoryAuthentication() // 메모리 상에 있는 사용자 정보
				.withUser("newlec")
					.password("{noop}111")
					.roles("ADMIN","MEMBER")
				.and()
				.withUser("dragon")
					.password("111")
					.roles("MEMBER");
			
	}

}
