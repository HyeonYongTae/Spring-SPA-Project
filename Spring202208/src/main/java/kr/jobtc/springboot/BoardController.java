package kr.jobtc.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.jobtc.springboot.board.BPageVo;
import kr.jobtc.springboot.board.BoardService;
import kr.jobtc.springboot.board.BoardVo;

@RestController
public class BoardController {
	
	
	@Autowired
	BoardService service;
	
	
	@RequestMapping("/board/board_select")
	public ModelAndView select(BPageVo pVo) {
		ModelAndView mv = new ModelAndView();
		//데이터 전달, 뷰의 역할 
		List<BoardVo> list =service.select(pVo);
		
	
		pVo=service.getpVo();
		
		mv.addObject("pVo",pVo);
		mv.addObject("list", list); //Model
		//setAttribute
		//addObject : key와 value를 담아 보낼 수 있는 메서드
		mv.setViewName("board/board_select"); /*WEB-INF/view/board/board_select.jsp */
		return mv;
		
		//spring container라는 저장공간 개념에 올라간다 
	}
	
	@RequestMapping("/board/board_view")
	public ModelAndView view(BoardVo bVo ,BPageVo pVo) {
		ModelAndView mv = new ModelAndView();
		
		
		//조회수 증가
		bVo = service.view(bVo.getSno(), "up");
		
		// doc안에 있는 \n 기호를 <br/>로 변경
		String temp= bVo.getDoc();
		bVo.setDoc(temp.replace("\n", "<br/>"));
		
		mv.addObject("bVo",bVo);
		mv.addObject("pVo",pVo);
		
		mv.setViewName("board/board_view"); /*WEB-INF/view/board/board_select.jsp */
		return mv;
		
		
	}
	@RequestMapping("/board/board_insert")
	public ModelAndView insert(BPageVo pVo) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("pVo",pVo);
		mv.setViewName("board/board_insert");
		return mv;
	}
	
	@RequestMapping("/board/board_repl")
	public ModelAndView repl(BPageVo pVo, BoardVo bVo) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("pVo",pVo);
		mv.addObject("bVo",bVo);
		
		mv.setViewName("board/board_repl");
		return mv;
	}
	
	
	@RequestMapping("/board/board_update")
	public ModelAndView update(BPageVo pVo) {
		ModelAndView mv = new ModelAndView();
		
		BoardVo bVo = service.view(pVo.getSno(), "");
		
		mv.addObject("pVo",pVo);
		mv.addObject("bVo",bVo);
		
		mv.setViewName("board/board_update");
		return mv;
	}
	
	
	@RequestMapping("/board/board_delete")
	public ModelAndView delete(BoardVo bVo ,BPageVo pVo) {
		String msg = "";
		ModelAndView mv = new ModelAndView();
		
		boolean b =service.delete(bVo);
		if( !b ) {
			msg = "삭제중 오류 발생";
		}
		
		mv = select(pVo);
		mv.addObject("msg", msg);
		mv.addObject("pVo",pVo);
		mv.setViewName("board/board_select"); 
		return mv;
	}
	
	
	@RequestMapping("/board/board10")
	public ModelAndView board10(){
		ModelAndView mv = new ModelAndView();
		List<BoardVo> list = service.board10();
		
		
		
		mv.addObject("list",list);
		mv.setViewName("/board/board_board10");
		
		return mv;
		
	}
	
}
