package kr.jobtc.springboot.aop_step2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AopConfig {
	
	//@Bean
	public AopDao getAopDao() {
		return new AopDao();
	}
	//@Bean
	public Logout getLogout() {
		return new Logout();
	}
}
