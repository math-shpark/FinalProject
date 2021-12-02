package mc.sn.cocoa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mc.sn.cocoa.service.CoachService;

@Controller
public class HomeController {
	@Autowired
	private CoachService coachService;

	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView home() throws Exception{
		ModelAndView mav = new ModelAndView();
		//홈 화면으로 연결
		String url = "/home";
		mav.setViewName(url);
		
		//coachList 호출
		List coachesList = coachService.listCoaches();
		
		//조회한 글 정보를 바인딩한 후 JSP로 전달
		mav.addObject("coachesList", coachesList);

		return mav;
	}
	
	
}