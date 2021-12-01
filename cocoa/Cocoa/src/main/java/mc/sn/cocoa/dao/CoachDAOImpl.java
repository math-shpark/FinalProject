package mc.sn.cocoa.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("coachDAO")
public class CoachDAOImpl implements CoachDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertNewCoach(Map coachMap) throws DataAccessException {
		int coachNO = selectNewCoachNO();
		coachMap.put("coachNO", coachNO);
		sqlSession.insert("mapper.coach.insertNewCoach", coachMap);
		return coachNO;
	}
	
	private int selectNewCoachNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.coach.selectNewCoachNO");
	}
}
