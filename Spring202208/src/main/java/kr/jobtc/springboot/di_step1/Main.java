package kr.jobtc.springboot.di_step1;

public class Main {
	
	public Main(Mysql m) {
		m.crud();
	}
	public Main(Oracle ora) {// 1차 변화 중복선언 
		ora.crud();
	}
	
	public Main(Mssql ms) {// 2차 변화 내부환경 바꾸는 작업
		ms.crud();
	}
	//문제점 : 외부환경의 변화에 따라서 내부 환경도 변화가 됐다.
	
	
	
	public static void main(String[] args) {
		Mysql my =new Mysql();  //원래 
		
		Main m= new Main(my);
		
		//외부 환경의 변화
		Oracle ora = new Oracle(); //1차 
		m = new Main(ora); 
		//오라클 타입의 객체를 집어넣는 생성자가 없음
		//매개변수 다르게 중복선언 해줌 
		
		Mssql ms = new Mssql(); //2차 
		m = new Main(ms);
		
	}
}
