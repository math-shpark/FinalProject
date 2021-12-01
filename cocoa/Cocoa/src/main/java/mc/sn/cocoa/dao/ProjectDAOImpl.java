package mc.sn.cocoa.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("projectDAO")
public class ProjectDAOImpl implements ProjectDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertProject(Map projectMap) {
		int projectNO = selectNewProjectNO();
		projectMap.put("projectNO", projectNO);
		sqlSession.insert("mapper.project.insertProject", projectMap);
		return projectNO;
	}

	private int selectNewProjectNO() {
		return sqlSession.selectOne("mapper.project.selectNewProjectNO");
	}
}