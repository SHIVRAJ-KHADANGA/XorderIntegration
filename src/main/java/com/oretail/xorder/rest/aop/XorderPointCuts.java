package com.oretail.xorder.rest.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class XorderPointCuts{

	@Pointcut("execution(* com.oretail.xorder.rest.controller.*.*(..))")
	public void forRestControllers() {} 
	
}