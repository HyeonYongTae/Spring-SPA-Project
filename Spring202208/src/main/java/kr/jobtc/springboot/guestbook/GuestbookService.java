package kr.jobtc.springboot.guestbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import kr.jobtc.springboot.mybatis.GuestbookMapper;


@Service
@Transactional
public class GuestbookService {
	GPageVo pVo;
	
	@Autowired
	GuestbookMapper mapper;
	//mapper 자동으로 가져오게끔 
	
	@Autowired
	PlatformTransactionManager manager;
	TransactionStatus status;
	
	
	public int getTotSize(GPageVo gVo) {
		int totSize=0;
		totSize = mapper.totSize(gVo);
		return totSize;
	}
	
	public List<GuestbookVo> select(GPageVo pVo){
		List<GuestbookVo> list =null;
		
		//(1)검색어에 해당하는 데이터 개수
		int totSize= getTotSize(pVo);
		//getTotSize(pVo)
		
		//(2)page계산
		pVo.setTotSize(totSize);
		pVo.compute();
		this.pVo=pVo;
		
		//(3)select
		list=mapper.select(pVo);
		
		return list;
	}
	
	public List<GuestbookVo> select10(){
		List<GuestbookVo> list =null;
		
		list=mapper.select10();
		
		return list;
	}
	

	public boolean insert(GuestbookVo gVo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint= status.createSavepoint();
		
		int cnt = mapper.insert(gVo);
		
		if(cnt>0) {
			b=true;
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		return b;
	}

	public boolean delete(GuestbookVo gVo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.delete(gVo);
		if(cnt>0) {
			b=true;
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		
		
		return b;
	}

	public boolean update(GuestbookVo gVo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.update(gVo);
		if(cnt>0) {
			b=true;
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		
		
		return b;
		
	}


	
	
	public GPageVo getpVo() {
		return pVo;
	}
	
}
