package com.newlecture.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
@EnableWebMvc
public class ControllerException {

	// 예외처리
	@ExceptionHandler(Exception.class)
	public String error(Exception e) {
		return "error";
	}

}
