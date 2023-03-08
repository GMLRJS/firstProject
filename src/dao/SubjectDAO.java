package dao;

import java.util.List;

import vo.CodingTestVO;
import vo.SubjectVO;
import mybatisDAO.MyBatisDAO;

public class SubjectDAO extends MyBatisDAO{
	
	private static SubjectDAO cding;
	
	private SubjectDAO() {
		
	}
	
	public static SubjectDAO getInstance() {
		if (cding == null) {
			cding = new SubjectDAO();
		}
		return cding;
	}
	
	public int checkSubject(String id) {
		
		int cnt = selectOne("subject.checkSubject", id);
		
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
	
	public int insertSubject(SubjectVO vo) {
		
		int cnt = insert("subject.insertSubject", vo);
		
		return cnt;
	}
	
	public int updateSubject(SubjectVO vo) {
		
		int cnt = update("subject.updateSubject", vo);
		
		return cnt;
		
	}
	
	public int deleteSubject(String id) {
		
		int cnt = delete("subject.deleteSubject", id);
		
		return cnt;
	}
}






