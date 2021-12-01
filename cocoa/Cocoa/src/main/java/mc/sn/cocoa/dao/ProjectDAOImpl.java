package mc.sn.cocoa.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("projectDAO")
public class ProjectDAOImpl implements ProjectDAO {
	@Autowired
	private SqlSession sqlSession;

	// 새 프로젝트 글 삽입
	@Override
	public int insertNewProject(Map projectMap) {
		int projectNO = this.selectNewProjectNO();
		projectMap.put("projectNO", projectNO);
		sqlSession.insert("mapper.project.insertNewProject", projectMap);
		return projectNO;
	}

	// 프로젝트 글 넘버링
	private int selectNewProjectNO() {
		return sqlSession.selectOne("mapper.project.selectNewProjectNO");
	}
}