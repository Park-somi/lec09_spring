package com.gn.spring.common.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // 빈을 'Aspect'로 사용
@Component // 자동으로 빈으로 등록해주는 어노테이션
public class AnnoLoggerService {
	Logger LOGGER = LogManager.getLogger(LoggerService.class);
	
	@Pointcut("execution( * com.gn.spring..*(..))")
	private void boardLogger() {}
	
	// 특정 메소드 실행 전에 동작할 메소드
//	@Before("boardLogger()") // advisor 지정
//	public void loggerBefore(JoinPoint jp) {
//		// 소속 클래스와 메소드의 이름
//		String className = jp.getTarget().getClass().getName();
//		String methodName = jp.getSignature().getName();
//		LOGGER.info(className+"."+methodName+"() 실행전");
//	}
	
	// 특정 메소드 실행 후에 동작할 메소드
	@After("boardLogger()") // advisor 지정
	public void loggerAfter(JoinPoint jp) {
		// 소속 클래스와 메소드의 이름
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		LOGGER.info(className+"."+methodName+"() 실행후");
	}
}
