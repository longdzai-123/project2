package com.example.project2.controller;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.example.project2.controller")
public class ExceptionController {
   //log org.slf4j.Logger
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// bat cac loai exception tai day 
	@ExceptionHandler({NoResultException.class})
	public String noResult(NoResultException ex) {
		logger.info("sql ex:", ex);
		return "404.html";//view
	}
	
	@ExceptionHandler({Exception.class})
	public String exception(Exception ex) {
		logger.error("ex:", ex);
		return "exception.html";//view
	}
	
}
