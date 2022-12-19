package kr.jobtc.springboot.aop_step1;

public class AopDao {
	
	public void select() {
		System.out.println("AopDao.select. . . . ");
		System.out.println("log . . . . select");
	}
	public void insert() {
		System.out.println("AopDao.insert. . . . ");
		System.out.println("log . . . . insert");
	}
	public void update() {
		System.out.println("AopDao.update. . . . ");
		System.out.println("log . . . . update");
	}
	public void delete() {
		System.out.println("AopDao.delete. . . . ");
		System.out.println("log . . . . delete");
	}
	
	
}
