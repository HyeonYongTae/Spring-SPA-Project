package kr.jobtc.springboot.di_step3;

public class Mysql implements Sql{
	
	public Mysql() {
		System.out.println("Mysql . . . .");
	}
	
	@Override
	public void crud() {
		System.out.println("Mysql.crud . . . .");
		
	}

	
}
