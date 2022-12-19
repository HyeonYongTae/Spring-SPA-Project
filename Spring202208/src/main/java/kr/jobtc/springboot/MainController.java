package kr.jobtc.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {
	
	@RequestMapping("/")
	//root가 들어오면
	public ModelAndView index() {
		//안쓰는 사람도 꽤 있음 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");// WEB-INF/view/index.jsp
		return mv;
	}
}
