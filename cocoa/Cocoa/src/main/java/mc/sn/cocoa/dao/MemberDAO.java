package mc.sn.cocoa.dao;

import mc.sn.cocoa.vo.MemberVO;

public interface MemberDAO {
	public int insertMember(MemberVO memberVO);
	public MemberVO loginById(MemberVO memberVO);
	public int idChk(MemberVO vo) throws Exception;
}
