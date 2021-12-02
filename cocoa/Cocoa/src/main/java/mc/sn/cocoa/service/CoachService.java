package mc.sn.cocoa.service;

import java.util.List;
import java.util.Map;

import mc.sn.cocoa.vo.CoachVO;

public interface CoachService {

	public int addNewCoach(Map coachMap);
	public List<CoachVO> listCoaches() throws Exception;
	public CoachVO viewCoach(int coachNO) throws Exception;
	public void modCoach(Map coachMap) throws Exception;
}
