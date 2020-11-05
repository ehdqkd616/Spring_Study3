package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAspect1 {
	
	private Logger logger = LoggerFactory.getLogger(LoggerAspect1.class);
	
	public Object loggerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature signature = joinPoint.getSignature(); // AOP가 적용되는 메소드 정보 반환
		logger.debug("signature : " + signature);

		String type = signature.getDeclaringTypeName(); // 메소드가 있는 클래스 풀네임
		String methodName = signature.getName(); // 타겟 객체의 메소드
		logger.debug("type : " + type);
		logger.debug("methodName : " + methodName);
		
		String componentName = "";
		if(type.indexOf("Controller") > -1) {
			componentName = "Controller \t: ";
		} else if(type.indexOf("Service") > -1) {
			componentName = "ServiceImpl	\t: ";
		} else if(type.indexOf("DAO") > -1) {
			componentName = "DAO	\t\t:	";
		}
		
		logger.debug("[Before]" + componentName + type + "." + methodName + "()");		
		
	    // Around Advice 활용 ->을 하기위해 loggerAdvice(JoinPoint joingPoint) 에서 ->loggerAdvice(ProceedingJoinPoint joinPoint)로 변경

		Object obj = joinPoint.proceed();
		logger.debug("[After]" + componentName + type + "." + methodName + "()");
	      
		return obj;
		
	}
}
	

