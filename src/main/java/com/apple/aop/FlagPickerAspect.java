package com.apple.aop;

import java.time.Duration;
import java.time.Instant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class FlagPickerAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(FlagPickerAspect.class);
	
	@Around("execution(* com.apple.controller.rest.*.*(..))")
	public Object restAPIServiceStartBeforeAndAfter(ProceedingJoinPoint joinPoint) throws Throwable{
	
		Instant start = Instant.now();
        String className = joinPoint.getTarget().getClass().getCanonicalName();
        String methodName = joinPoint.getSignature().getName();
        LOGGER.info("*****************************[ method start ]*************************************************************************************************************");
        LOGGER.info("started method : " + className+"."+methodName);

        Object obj;
        try {
            obj = joinPoint.proceed();
            return obj;
        } catch (Throwable ex) {
        	ex.printStackTrace();
        	LOGGER.error("Method : "+className+"."+methodName+" Exception ->{}",ex);
            throw ex;
        }finally {
        	Instant end = Instant.now();
        	Duration timeElapsed = Duration.between(start, end); 
        	LOGGER.info("elapsed time for method : " + className+"."+methodName +" is -> [ "+timeElapsed.toMillis() +" ms ]");        	
        	LOGGER.info("*************************************[ method end ]******************************************************************************************************");
        }
	}
	
	@Around("execution(*  com.apple.util.*.*(..))")
	public Object readAJsonFileStartBeforeAndAfter(ProceedingJoinPoint joinPoint) throws Throwable{
	
		Instant start = Instant.now();
        String className = joinPoint.getTarget().getClass().getCanonicalName();
        String methodName = joinPoint.getSignature().getName();
        LOGGER.info("*****************************[ method start ]*************************************************************************************************************");
        LOGGER.info("started method : " + className+"."+methodName);

        Object obj;
        try {
            obj = joinPoint.proceed();
            return obj;
        } catch (Throwable ex) {
        	ex.printStackTrace();
        	LOGGER.error("Method : "+className+"."+methodName+" Exception ->{}",ex);
            throw ex;
        }finally {
        	Instant end = Instant.now();
        	Duration timeElapsed = Duration.between(start, end); 
        	LOGGER.info("elapsed time for method : " + className+"."+methodName +" is -> [ "+timeElapsed.toMillis() +" ms ]");        	
        	LOGGER.info("*************************************[ method end ]******************************************************************************************************");
        }
	}
}
