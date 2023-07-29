package dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import mybatisDAO.MyBatisDAO;
import vo.SubjectVO;

public class SubjectDAO extends MyBatisDAO{
	
	private static SubjectDAO subject;
	
	private SubjectDAO() {
		
	}
	
	public static SubjectDAO getInstance() {
		if (subject == null) {
			subject = new SubjectDAO();
		}
		return subject;
	}
	public int insertSubject(SubjectVO sv) {

		int cnt = insert("subject.insertSubject", sv);

		return cnt;
	}

	public int updateSubject(SubjectVO sv) {

		int cnt = update("subject.updateSubject", sv);

		return cnt;
	}

	public int checkSubject(String id) {

		int cnt = selectOne("subject.checkSubject",id);

		return cnt;
	}

	public int deleteSubject(String subjectID) {

		int cnt = delete("subject.deleteSubject", subjectID);

		return cnt;
	}
	public SubjectVO selectOneSubject(String id) {
		
		SubjectVO vo  = selectOne("subject.selectOneSubject",id);
		
		return vo;
	}
	
	public List<SubjectVO> selectSubject(SubjectVO vo) {
		
		List<SubjectVO> list = selectOne("subject.selectSubject", vo);
		
		return list;
	}
	
	public List<SubjectVO> selectAllSubject(){
		
		List<SubjectVO> list = selectList("subject.selectAllSubject");
		
		return list;
	}
}






