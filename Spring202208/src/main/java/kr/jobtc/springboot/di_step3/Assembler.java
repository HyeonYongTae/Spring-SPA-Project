package kr.jobtc.springboot.di_step3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration 
public class Assembler {
	
	//Configuration을 써야 Bean을 쓸 수 있음 
	//@Bean
	public Sql getSql() {
		return new Oracle();
		//외부 환경이 바뀌면 이 부분만 바꿔주면됨
		//getSql이라는 결과 자체가 하나의 Bean이다.
		//이것으로 인해서 객체가 생성됐음 
		//Sysout이 뿌려지는 것을 확인할 수 있음
		//객체가 메모리에 올라가 있는 상태
		//스프링이 실행되면서 알아서 메모리에 올려놓음 
		
	}
	
}
