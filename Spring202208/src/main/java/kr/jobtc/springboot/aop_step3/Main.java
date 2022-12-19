package kr.jobtc.springboot.aop_step3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=
				new AnnotationConfigApplicationContext(AopConfig.class);
		
		AopDao dao = (AopDao)context.getBean("getAopDao");
		dao.select();
		dao.insert();
		dao.update();
		dao.delete();
		
		AroundTest at = (AroundTest)context.getBean("getAroundTest");
		at.crud();//jp.proceed() 메소드에 의해서 실행되는 것임 
		
		System.out.println("-".repeat(50));
		StudentDao sDo = (StudentDao)context.getBean("getStudentDao");
		sDo.select();
		sDo.insert();
		sDo.update();
		sDo.delete();
	}
}
