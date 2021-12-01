package mc.sn.cocoa.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface CoachDAO {
	public int insertNewCoach(Map coachMap) throws DataAccessException ;
}
