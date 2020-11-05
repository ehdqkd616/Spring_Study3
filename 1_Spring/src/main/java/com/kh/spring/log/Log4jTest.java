package com.kh.spring.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Log4jTest {

	private Logger logger = LoggerFactory.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		new Log4jTest().test();
	}
	
	public void test() {
		logger.trace("trace 로그");
		logger.debug("debug 로그");
		logger.info("info 로그");
		logger.warn("warn 로그");
		logger.error("error 로그");
//		logger.fatal("fatal 로그"); // Logger는 fatal레벨을 지원하지 않음
		
		
	}

}
