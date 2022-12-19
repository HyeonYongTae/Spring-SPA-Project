package kr.jobtc.springboot.board;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
	static String path = "C:\\Users\\dmdke\\eclipse-workspace\\Spring202208\\src\\main\\resources\\static\\upload\\";
	
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/board_insertR")
	public synchronized String upload(@RequestParam("attFile")  List<MultipartFile> mul,
			                        @ModelAttribute BoardVo vo) {
		//상호배타적 동기화
		 String msg="";
		try {
			System.out.println("id : " + vo.getId());
			System.out.println("subject: " + vo.getSubject());
			List<AttVo> attList = new ArrayList<AttVo>();
			
			//본문을 저장
			attList = fileupload(mul);
			vo.setAttList(attList);
			boolean flag =service.insertR(vo);
			if(!flag) {
			
				msg="저장중 오류 발생";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			msg="정상적이지만 메시지 표시";
		}
		
		return msg;
	}
	
	@RequestMapping("/board/board_updateR")
	public String updateR(@RequestParam("attFile") List<MultipartFile> mul,
						  @ModelAttribute BoardVo bVo ,@ModelAttribute BPageVo pVo, //객체가 있는 get set가 있는건 ModelAttribute
						  @RequestParam(name="delFile", required= false) String[] delFiles ){    			// List,String 기본형은 Param 으로 받아야한다
						 //required 파이리 없는 것도 수정되게 하려고 required 지정 안해주고 파일이 없는걸 수정하면 delFile이 없다고 뜬다
							
	
		
		String msg = " ";
		try {
			
			List<AttVo> attList = fileupload(mul);
			bVo.setAttList(attList);

			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		boolean flag = service.updateR(bVo, delFiles);
		if(!flag) msg = "수정중 오류 발생";

		
		return msg;
	}

	//file upload 공통부분 (insertR, updateR, repleR)
	   //file upload 공통부분(입력, 수정, 댓글)
	   public List<AttVo> fileupload(List<MultipartFile> mul)throws Exception{
	      List<AttVo> attList = new ArrayList<AttVo>();
	      
	      for(MultipartFile m : mul) {
	         if(m.isEmpty()) continue; 
	         
	         
	         UUID uuid = UUID.randomUUID(); //랜덤 숫자. 이름중복 방지
	         String oriFile = m.getOriginalFilename();
	         String sysFile = "";
	         File temp = new File(path + oriFile);//임시 저장
	         m.transferTo(temp); // 데이터를 지정한 파일로 저장
	         //실제로 저장이 되게하는 코드 
	         
	         sysFile = (uuid.getLeastSignificantBits()*-1) + "-" + oriFile; //경로+랜덤문자+파일명
	         File f = new File(path + sysFile); //파일객체 만들고
	         temp.renameTo(f); //이름 바꿔줌
	         
	         AttVo attVo = new AttVo();//boardVo가 먼저 만들어져서 sno가 있어야 pSno도 추가되어야겠지
	         attVo.setOriFile(oriFile);
	         attVo.setSysFile(sysFile);
	         //attVo.setpSno(pVo.getSno()); //미확정
	         
	         attList.add(attVo); //이거 있어야 넘어감
	         
	         System.out.println(uuid.toString());
	      }
	      
	      return attList;
	   }
	   
	   @RequestMapping("/board/board_replR")
		public synchronized String replR(@RequestParam("attFile")  List<MultipartFile> mul,
				                        @ModelAttribute BoardVo bVo, @ModelAttribute BPageVo pVo) {
			//상호배타적 동기화
			 
			try {
				List<AttVo> attList = new ArrayList<AttVo>();
				attList = fileupload(mul);
				bVo.setAttList(attList);
			
				//본문을 저장
				boolean flag =service.replR(bVo);
				if(!flag) return "저장중 오류 발생";
				
				
				service.insertAttList(attList);
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
			return "redirect:/board/board_select";
		}
	   
}

