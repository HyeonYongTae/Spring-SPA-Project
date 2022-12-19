package kr.jobtc.springboot.aop_step3;

public class StudentDao {
	
	public void select() {
		System.out.println("Student select . . . . ");
	}
	public void insert() {
		System.out.println("Student insert . . . . ");
	}
	public void update() {
		System.out.println("Student update. . . . ");
}
	public void delete() {
		System.out.println("Student delete . . . .");
	}
}
