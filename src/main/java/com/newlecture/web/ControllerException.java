package com.newlecture.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerException {
   
   @ExceptionHandler(IndexOutOfBoundsException.class)
   @ResponseBody
   public String bound(Exception e) {
      
      return "bound error";
   }
   
   @ExceptionHandler(Exception.class)
   public String error(Exception e, Model model) {
      
      model.addAttribute("message",e.getMessage());
      return "error";
   }
}