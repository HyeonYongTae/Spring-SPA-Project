package kr.jobtc.springboot.di_step2;

public class Main {
	
	public Main(Sql sql) {
		sql.crud();
	}
	
	public static void main(String[] args) {
		//직접 객체를 만들지 않음 
		//외부조립기 사용 
		//변화가 생기면 외부조립기 코드만 바꾸면 됨 
		Assembler ab = new Assembler();
		Main m = new Main(ab.getSql());
		//메인 코드는 바뀌지 않고 
		//Assembler 코드만 열어서 코드만 수정 
	}
}
