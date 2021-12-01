package mc.sn.cocoa.service;

import mc.sn.cocoa.vo.MemberVO;

public interface MemberService {
	public int joinMember(MemberVO member);
	public MemberVO login(MemberVO memberVO);
	public int idChk(MemberVO vo) throws Exception;
}
