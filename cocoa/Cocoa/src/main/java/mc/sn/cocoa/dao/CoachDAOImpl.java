package mc.sn.cocoa.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
