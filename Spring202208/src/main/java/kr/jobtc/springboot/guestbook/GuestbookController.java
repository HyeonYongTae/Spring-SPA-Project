package kr.jobtc.springboot.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GuestbookController {
	
	
	//@Autowired
	//GuestbookDao dao;
	//자동으로 dao 물고 들어옴 
	
	@Autowired
	GuestbookService service;
	
	@RequestMapping(value="/guestbook/guestbook_select",method = RequestMethod.GET)	
	public ModelAndView select(GPageVo pVo) {//★파라미터로 던진 값을 알아서 받는다
		//<jsp useBean >, <jsp setParameter> 자동으로 수행
		
		
		// 검색에어 따른 totSize를 가져와 Page를 재계산 
		//int totSize=service.getTotSize(pVo);
		
		// 검색어에 따른 List를 가져옴
		
		// List를 mv에 추가 
		
		ModelAndView mv = new ModelAndView();
		//Service or dao(direct)
		//Sysout(dao); null이뜸 
		
		
		List<GuestbookVo> list = service.select(pVo);
	
		pVo=service.getpVo(); //새로 갱신된 페이지정
		
		System.out.println("now page : " + pVo.getNowPage());
		System.out.println("tot page : "+ pVo.getTotPage());
		
		
		mv.addObject("pVo",pVo);
		mv.addObject("list",list);
		//컨트롤의 역할은 끝 
		
		mv.setViewName("/guestbook/guestbook_select");
		return mv;
	}
	
	@RequestMapping(value="/guestbook/guestbook10")	
	public ModelAndView select10() {//★파라미터로 던진 값을 알아서 받는다
		
		
		ModelAndView mv = new ModelAndView();
		
		
		List<GuestbookVo> list = service.select10();
	
		
		mv.addObject("list",list);
		mv.setViewName("/guestbook/guestbook_select10");
		
		return mv;
	}
	
	@RequestMapping(value="/guestbook/guestbook_insert")	
	public void insert(GuestbookVo gVo, HttpServletResponse resp) {
		
		System.out.println(gVo.getId());
		System.out.println(gVo.getDoc());
		
		boolean b = service.insert(gVo);
		
		try {
			PrintWriter out=resp.getWriter();
			if(b) {
				out.print("<script>");
				out.print(" alert('Save success')" );
				out.print("</script>");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/guestbook/guestbook_delete")	
	public void delete(GuestbookVo gVo, HttpServletResponse resp) {
		
		
		System.out.println("delete" + gVo.getId());
		System.out.println("delete" + gVo.getDoc());
		System.out.println("delete" + gVo.getPwd());
		System.out.println("detele" +gVo.getSno());
		
		boolean b = service.delete(gVo);
		
		try {
			PrintWriter out=resp.getWriter();
			if(b) {
				out.print("<script>");
				out.print(" alert('delete success')" );
				out.print("</script>");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/guestbook/guestbook_update")	
	public void update(GuestbookVo gVo, HttpServletResponse resp) {
		
		
		System.out.println("update" + gVo.getId());
		System.out.println("update" + gVo.getDoc());
		System.out.println("update" + gVo.getPwd());
		System.out.println("update" +gVo.getSno());
		
		boolean b = service.update(gVo);
		
		try {
			PrintWriter out=resp.getWriter();
			if(b) {
				out.print("<script>");
				out.print(" alert('update success')" );
				out.print("</script>");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}

