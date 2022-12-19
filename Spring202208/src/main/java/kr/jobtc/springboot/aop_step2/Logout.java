package kr.jobtc.springboot.aop_step2;

public class Logout {
	AopDao dao;
	
	public void run(AopDao dao, String job) {
		this.dao=dao;
		System.out.println("log . . . . AopDao . . . . 1");
		
		switch(job) {
		case "select": //횡단적 관심사의 조인 포인트
			this.dao.select();
			break;
		case "update":
			this.dao.update();
			break;
		case "insert":
			this.dao.insert();
			break;
		case "delete":
			this.dao.delete();
			break;
		}
		//잡을 하고 난 후에 after 둘다 쓰면 around
		System.out.println("log . . . . AopDao . . . . 2");
		
	}
}
