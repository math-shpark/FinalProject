package mc.sn.cocoa.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mc.sn.cocoa.dao.CoachDAO;

@Service("coachService")
@Transactional(propagation = Propagation.REQUIRED)
public class CoachServiceImpl implements CoachService {
	@Autowired
	private CoachDAO coachDAO;

	@Override
	public int addNewCoach(Map coachMap) throws Exception {
		return coachDAO.insertNewCoach(coachMap);
	}
}
