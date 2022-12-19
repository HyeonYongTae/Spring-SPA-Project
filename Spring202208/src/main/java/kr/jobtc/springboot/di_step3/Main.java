package kr.jobtc.springboot.di_step3;

import java.lang.annotation.Annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		//Spring 앱과는 관계없이 단독으로 실행할 수 있는 코드
		//Spring이 제공해주는 di 사용 
		//Spring의 Assembler 가져오는 코드 
		//엄청나게 중요한 main코드 
		
			AnnotationConfigApplicationContext context=
					//설정값을 가져다 주는 코드 
					new AnnotationConfigApplicationContext(Assembler.class);
			
			Sql sql = (Sql)context.getBean("getSql");
			sql.crud();
		//Oracle,Mssql로 바꾸고싶으면 ->Assembler 바꿔주면됨
		
	}
}
