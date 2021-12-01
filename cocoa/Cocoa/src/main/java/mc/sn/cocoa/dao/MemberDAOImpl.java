package mc.sn.cocoa.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mc.sn.cocoa.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertMember(MemberVO memberVO) {
		int result = 0;
		result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}
	
	@Override
	public MemberVO loginById(MemberVO memberVO) {
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);
		return vo;
	}
	
	@Override
	public int idChk(MemberVO vo) throws Exception {
		System.out.println(vo.getId());
		int result = sqlSession.selectOne("mapper.member.idChk", vo);
		System.out.println(result);
		return result;
	}
}
