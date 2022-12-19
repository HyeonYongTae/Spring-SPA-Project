package kr.jobtc.springboot.aop_step3;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableAspectJAutoProxy
@Aspect
//@Configuration
public class AopConfig {
	
	
	//@Bean
	public AopDao getAopDao() {
		return new AopDao();
	}
	
	//@Bean
	public AroundTest getAroundTest() {
		return new AroundTest();
	}
	
	//@Bean
	public StudentDao getStudentDao() {
		return new StudentDao();
	}
	
	//언제 받을거냐 
	@Before("execution(* kr.jobtc.springboot.aop_step3.AopDao.*(..))")
	public void beforeAdvice(JoinPoint jp) {
		//Proxy 프로그램 인터셉트 가로챔 
		//aop가 구현되기 위한 장소 =JoinPoint 
			System.out.println("log . . . . . . . . . .");
			// 실행될 메서드명
			System.out.println(jp.getSignature().getName());
	}
	
	@Around("execution(* kr.jobtc.springboot.aop_step3.AroundTest.*(..))")
		public void aroundAdvice(ProceedingJoinPoint jp) {
			//AroundTest라는 클래스라는 모든 메서드가 weaving 될 지점
			System.out.println("비즈니스 로직을 처리하기전에 . . . . . . ");
			try {
				jp.proceed();//비즈니스 로직 실행 
			} catch (Throwable e) {
				e.printStackTrace();
			}
			System.out.println("비즈니스 로직을 처리하고난 후에 . . . . . . ");
	}
	
	@Before("execution(* kr.jobtc.springboot.aop_step3.StudentDao.select(..))")
		public void sDo_beforeAdvice(JoinPoint jp) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(E) hh:mm:ss:SS");
			Date d =Calendar.getInstance().getTime();
			
			//newDate()
			//Date->향후 업그레이드 버전에서는 제외될 수 있다, 기능 향상 없다
			//디플리
			
			String nal = sdf.format(d);
		
		//실행될 메서드명
			String log = (nal + " >>> " + jp.getSignature().getName());
			System.out.println(log);
			writeLog(log);
	}
		
	@Around("execution(* kr.jobtc.springboot.aop_step3.StudentDao.insert(..))"
			+ " || execution(* kr.jobtc.springboot.aop_step3.StudentDao.update(..))"
			+ " || execution(* kr.jobtc.springboot.aop_step3.StudentDao.delete(..))")
	public void studDaoAroundAdvice(ProceedingJoinPoint jp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(E) hh:mm:ss:SS");
		Date d =Calendar.getInstance().getTime();
		
		String nal = sdf.format(d);
		//실행될 메서드명
		String log = "login pass : "
							+ (nal + " >>> " + jp.getSignature().getName());
		
		System.out.println(nal + " >>> " + jp.getSignature().getName());
		
		try {
			jp.proceed();
			
		}catch( Throwable e) {
			e.printStackTrace();
		}
		log +=("... end of around aop . . . . .");
		
		System.out.println("... end : " + jp.getSignature().getName());
		
		writeLog(log);
	
	}
	
	public void writeLog(String log) {
		//log file
		FileWriter fw;
		SimpleDateFormat fileNameFrm = new SimpleDateFormat("yyyy-MM-dd-hh");
		String logFile = fileNameFrm.format(new Date()) + ".log";
		
		try {
			fw= new FileWriter(logFile, true);
			//FileWriter를 생성하면 지정된 파일이 이미 있을 경우 그 파일을 덮어쓴다. 따라서 기존의 파일 내용은 없어진다.
			//기존 파일 내용 끝에 데이터를 추가할 경우 두번 째 매개값에 true를 주면됨

			fw.write(log);
			fw.write("\n");
			fw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
