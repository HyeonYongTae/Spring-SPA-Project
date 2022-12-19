package kr.jobtc.springboot.board;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import kr.jobtc.springboot.mybatis.BoardMapper;

@Service
@Transactional
public class BoardService {
	BPageVo pVo;
	
	@Autowired
	BoardMapper mapper;
	Object savePoint;
	
	@Autowired
	PlatformTransactionManager manager;
	TransactionStatus status;
	
	public List<BoardVo> select(BPageVo pVo){
		
		int totSize=mapper.totList(pVo);
		System.out.println(totSize);
		
		pVo.setTotSize(totSize);
		this.pVo =pVo;
		System.out.println(pVo.totSize);
		
		
		List<BoardVo> list = mapper.select(pVo);
		//mapper.select는 BoardMapper의 select를 부르고 그것은 binding 되어있음 
		return list;
	}

	public List<BoardVo> board10() {
		List<BoardVo> list = mapper.board10();
		return list;
	}


	public BoardVo view(int sno, String up) {
		BoardVo bVo = null;
		if(up !=null && up.equals("up")) {
			mapper.hitUp(sno);
		}
		bVo = mapper.view(sno);
		
		List<AttVo> attList= mapper.attList(sno);
		bVo.setAttList(attList);
		
		return bVo;
	}
	
	public BPageVo getpVo() {
		return pVo;
	}

	public boolean delete(BoardVo bVo) {
		boolean b =true;
		
		//자신의 글에 댓글이 있는지 판단하기 
		//같은 grp안에 자신의 seq보다 1더 큰 seq의 자료ㅗ에서
		//deep이 자신 보다 큰것이 있으면 댓글이 있는 것임.
		int replCnt = mapper.replCheck(bVo);
		
		if(replCnt>0) {
			b=false;
			return b;
		}
		
		//sno에 해당하는 테이블 삭제
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.delete(bVo);
		if(cnt<1) {
			b=false;
		}else {
			//sno를 pSno로 바꾸어 첨부 테이블에서 첨부파일 목록 가져오기
			List<String> attList = mapper.delFileList(bVo.getSno());
			//첨부 테이블 삭제
			if(attList.size()>0) {
				cnt=mapper.attDeleteAll(bVo.getSno());
				if(cnt>0) {
					//첨부 파일 삭제
					if(attList.size()>0) {
						//String[] delList = attList.toArray(new String[0]);
						//fileDelete(delList);
					}
				}else {
					b=false;
				}
			}
		}
		if(b) manager.commit(status);
		else status.rollbackToSavepoint(savePoint);
		
		return false;
	}

	public boolean insertR(BoardVo vo) {
		status = manager.getTransaction(new DefaultTransactionDefinition());
	    savePoint = status.createSavepoint();
	    
	    boolean flag=true;
		
		int cnt = mapper.insertR(vo);
		if(cnt<1) {
			flag =false;
			
		}else if (vo.getAttList().size()>0) {
			int attCnt= mapper.insertAttList(vo.getAttList());
			if(attCnt<0) flag=false;
		}
		
		if(flag) {
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
			String[] delFiles= new String[vo.getAttList().size()];
			for(int i=0; i<vo.getAttList().size(); i++) {
				delFiles[i] = vo.getAttList().get(i).getSysFile();
			}
			fileDelete(delFiles);
		}
		return flag;
	}

	public void insertAttList(List<AttVo> attList) {
		
		int cnt = mapper.insertAttList(attList);
		
		if(cnt>0) {
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		
	}

	public boolean updateR(BoardVo bVo, String[] delFiles) {
	      status = manager.getTransaction(new DefaultTransactionDefinition());
	       this.savePoint = status.createSavepoint(); //필드에 있는거 쓸래
	         
	      boolean b = true;
	      int cnt = mapper.update(bVo);
	      
	      if(cnt<1) {
	         b=false;
	      }else if(bVo.getAttList().size()>0){
	         int attCnt = mapper.attUpdate(bVo);
	         if(attCnt<1) b= false;
	      }
	      
	      if(b) {
	         manager.commit(status);
	         
	         if(delFiles != null && delFiles.length>0) {
	            //첨부파일 데이터 삭제
	            cnt = mapper.attDelete(delFiles);
	            if(cnt>0) {
	               fileDelete(delFiles);
	            }else {
	               b = false;
	            }
	         }
	      }else {
	         status.rollbackToSavepoint(savePoint);
	      }
	      
	      return b;
	   }
		
public void fileDelete(String[] delFiles) {
	
		for (String f : delFiles) {
			
			File file = new File(FileUploadController.path + f);
			if (file.exists())
				file.delete();
		}
	}

public boolean replR(BoardVo bVo) {
	status = manager.getTransaction(new DefaultTransactionDefinition());
    savePoint = status.createSavepoint();
	
	 boolean b=true;
    mapper.seqUp(bVo);
     int cnt = mapper.replR(bVo);
     
     if(cnt<1) {
         b=false;
     }else if(bVo.getAttList().size()>0) {
    	 int attCnt= mapper.insertAttList(bVo.getAttList());
    	 if(attCnt<0) b=false;
     }
    
     if(b) manager.commit(status);
     else  {
    	 status.rollbackToSavepoint(savePoint);
    	 
    	 String[] delFiles= new String[bVo.getAttList().size()];
    	 for(int i=0; i<bVo.getAttList().size(); i++) {
				delFiles[i] = bVo.getAttList().get(i).getSysFile();
			}
			fileDelete(delFiles);
    	 
     
     }
     return b;
}
	
}
