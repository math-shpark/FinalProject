package mc.sn.cocoa.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mc.sn.cocoa.service.MemberService;
import mc.sn.cocoa.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {
	@Autowired
	private MemberService memberService;

	// 회원가입 화면으로 이동
	@Override
	@RequestMapping(value = "/view_join", method = RequestMethod.GET)
	public ModelAndView view_join(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/joinForm";
		mav.setViewName(url);
		return mav;
	}

	// 로그인 화면으로 이동
	@Override
	@RequestMapping(value = "/view_login", method = RequestMethod.GET)
	public ModelAndView view_login(@RequestParam(value = "result", required = false) String result,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/loginForm";
		mav.addObject("result", result);
		mav.setViewName(url);
		return mav;
	}

	// 회원가입
	@Override
	@ResponseBody
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public int join(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = memberService.joinMember(memberVO);
		System.out.println(result);
		return result;
	}

	// 로그인
	@Override
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		MemberVO memberVO = memberService.login(member);
		if (memberVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
			mav.setViewName("redirect:/");
		} else {
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/view_login");
		}
		return mav;
	}

	//로그아웃
	@Override
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
	
	//아이디 체크
	@ResponseBody
	@RequestMapping(value="/idChk", method = RequestMethod.POST)
	public int idChk(MemberVO vo)  throws Exception{
		int result = 0;
		System.out.println(vo.getId());
		result = memberService.idChk(vo); 
		System.out.println(result);
		return result;
	}
	
	//멤버컨트롤러 - 정준
}
