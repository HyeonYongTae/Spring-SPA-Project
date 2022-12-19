package kr.jobtc.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//활성화를 시켜줘야 사용할 수 있음
@SpringBootApplication
@EnableAspectJAutoProxy
//AspectJAutoProxy 백그라운드에서 처리되는거를 가로채는 그런것을 활성화 하겠다
//실행되는 코드들을 Intercept하는 것을 AutoProxy

public class Spring202208Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Spring202208Application.class, args);
	}
	
}
