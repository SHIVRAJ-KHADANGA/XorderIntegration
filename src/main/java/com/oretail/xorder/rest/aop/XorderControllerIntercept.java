package com.oretail.xorder.rest.aop;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@Aspect
public class XorderControllerIntercept {
	private static final Logger LOGGER = LogManager.getRootLogger();
	
	@Before("com.oretail.xorder.rest.aop.XorderPointCuts.forRestControllers()")
	public void beforeAdviceToValidateHTTPHeaders(JoinPoint theJoinPoint) {
		HttpHeaders headers = null;
		LOGGER.info("\n=====>>> Executing @Before advice on method");	
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		LOGGER.info("Method: " + methodSig);
		
		// display method arguments
		
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		// loop thru args
		for (Object tempArg : args) {
			LOGGER.info(tempArg.toString());
			
			if (tempArg instanceof HttpHeaders) {
				
				// downcast and get needed header value
				 headers = (HttpHeaders) tempArg;		
			     List<String> headerStrs = headers.get("Accept-Version");
			     if(headerStrs == null) {
			    	 throw new RuntimeException("Accept-Version header is required");
			     }
			     else if(headerStrs.get(0).compareTo("1.0")!=0) {
			    	 throw new RuntimeException("Incompatible Accept-Version : "+headerStrs.get(0));
			     }
			}
		}
		
	}

}
