package kr.jobtc.springboot.aop_step2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=
				new AnnotationConfigApplicationContext(AopConfig.class);
		
		AopDao dao = (AopDao)context.getBean("getAopDao");
		Logout logout = (Logout)context.getBean("getLogout");
		logout.run(dao, "select");
		logout.run(dao, "insert");
		//제3의 클래스 logout 실행을 해야 
		//순수 자바로 만든 클래스의 이질감 
	}
}
