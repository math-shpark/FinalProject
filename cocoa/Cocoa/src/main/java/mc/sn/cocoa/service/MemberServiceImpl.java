package mc.sn.cocoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mc.sn.cocoa.dao.MemberDAO;
import mc.sn.cocoa.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	
	@Override
	public int joinMember(MemberVO member) {
		int result = 0;
		result=memberDAO.insertMember(member);
		return result;
	}
	
	@Override
	public MemberVO login(MemberVO memberVO) {
		return memberDAO.loginById(memberVO);
	}
	
	@Override
	public int idChk(MemberVO vo)  throws Exception{
		int result = memberDAO.idChk(vo);
		return result;
	}
}
