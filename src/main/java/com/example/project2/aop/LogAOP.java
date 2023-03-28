package com.example.project2.aop;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAOP {
	@Autowired
	CacheManager cacheManager;
    
	@Before("execution(* com.example.project2.service.UserService.getById(*))")
	public void create(JoinPoint joinpoint){
		int id = (Integer) joinpoint.getArgs()[0];
		log.info("HELLOOOOOO" + id);
	}
	@After("execution(* com.example.project2.service.UserService.deleteAll(*))")
	public void delete(JoinPoint joinpoint){
		List<Integer> ids = (List<Integer>) joinpoint.getArgs()[0];
		log.info("DELETE ALL" + ids.toString());
		for(int id : ids) {
			cacheManager.getCache("users").evict(id);
		}
	}
	
}
