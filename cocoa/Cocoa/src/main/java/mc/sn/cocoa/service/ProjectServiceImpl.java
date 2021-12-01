package mc.sn.cocoa.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mc.sn.cocoa.dao.ProjectDAO;

@Service("projectService")
@Transactional(propagation = Propagation.REQUIRED)
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectDAO projectDAO;
	
	@Override
	public int addProject(Map projectMap) {
		int result = 0;
		result = projectDAO.insertProject(projectMap);
		return result;
	}
}
