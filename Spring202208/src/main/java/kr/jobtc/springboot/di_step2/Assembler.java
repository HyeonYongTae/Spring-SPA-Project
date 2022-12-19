package kr.jobtc.springboot.di_step2;

//객체를 생성하여 리턴해주는 외부 조립기 역할
public class Assembler {
	
	public Sql getSql() {//getSql()이라는 메소드를 부르면 
		
		return new Mssql();
		//외부 환경이 바뀌면 내부 환경은 바뀌지 않고
		//이 부분만 바뀜 바뀌더라도 최소의 요건만 바뀜
		
	}
	
}
