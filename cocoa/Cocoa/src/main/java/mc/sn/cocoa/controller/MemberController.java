package mc.sn.cocoa.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mc.sn.cocoa.vo.MemberVO;

public interface MemberController {
	public ModelAndView view_join(HttpServletRequest request, HttpServletResponse response);

	public int join(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException;

	public ModelAndView view_login(@RequestParam(value = "result", required = false) String result,
			HttpServletRequest request, HttpServletResponse response);

	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response);

	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response);

}
