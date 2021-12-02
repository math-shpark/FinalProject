package mc.sn.cocoa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import mc.sn.cocoa.vo.CoachVO;

@Repository("coachDAO")
public class CoachDAOImpl implements CoachDAO{
	@Autowired
	private SqlSession sqlSession;
	
	// 새 코칭 글 삽입
	@Override
	public int insertNewCoach(Map coachMap) {
		int coachNO = this.selectNewCoachNO();
		coachMap.put("coachNO", coachNO);
		sqlSession.insert("mapper.coach.insertNewCoach", coachMap);
		return coachNO;
	}
	
	// 코칭 글 넘버링
	private int selectNewCoachNO() {
		return sqlSession.selectOne("mapper.coach.selectNewCoachNO");
	}
	
	@Override
	public List selectAllCoachesList() throws DataAccessException {
		//전체 조회 sql 문 호출
		List<CoachVO> coachesList = sqlSession.selectList("mapper.coach.selectAllCoachesList");
		return coachesList;
	}
	
	// 코칭 글 조회
	@Override
	public CoachVO selectCoach(int coachNO) throws DataAccessException {
		return sqlSession.selectOne("mapper.coach.selectCoach", coachNO);
	}
	
	@Override
	public void updateCoach(Map coachMap) throws DataAccessException {
		sqlSession.update("mapper.coach.updateCoach", coachMap);
	}
}
