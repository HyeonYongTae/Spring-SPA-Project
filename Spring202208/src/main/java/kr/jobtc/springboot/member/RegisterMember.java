package kr.jobtc.springboot.member;



public class RegisterMember implements Member {
	
	public RegisterMember() {
		System.out.println("RegisterMember . . . .");
	}
	
	@Override
	public void crud() {
		System.out.println("RegisterMember.Crud . . . . ");
		
	}
	
	
}
