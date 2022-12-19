package kr.jobtc.springboot.member;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class Assembler {
	
	//Interface하나당 1Bean
	//@Bean
	public Member getMember() {
		
		return new RegisterMember();
	}
	
}
