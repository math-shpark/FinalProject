package mc.sn.cocoa.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import mc.sn.cocoa.vo.CoachVO;

public interface CoachDAO {
	
	public int insertNewCoach(Map coachMap);
	public List selectAllCoachesList() throws DataAccessException;
	public CoachVO selectCoach(int coachNO) throws DataAccessException;
	public void updateCoach(Map coachMap) throws DataAccessException;
}
