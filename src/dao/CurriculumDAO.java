package dao;

import java.util.List;

import mybatisDAO.MyBatisDAO;
import vo.CurriculumVO;

public class CurriculumDAO extends MyBatisDAO {

	private static CurriculumDAO curriculumDAO;

	private CurriculumDAO() {
	}

	public static CurriculumDAO getInstance() {

		if (curriculumDAO == null) {
			curriculumDAO = new CurriculumDAO();
		}
		return curriculumDAO;
	}
	
	
	public List<CurriculumVO> selectCurriculum(){
		
		List<CurriculumVO> curriList = selectList("curriculum.selectCurriculum");
		
		return curriList;
	}
	
	public CurriculumVO selectOneCurriculum(String id) {
		
		CurriculumVO vo = selectOne("curriculum.selectOneCurriculum", id);
		
		return vo;
	}
	
	public int insertCurriculum(CurriculumVO vo) {
		
		int cnt = insert("curriculum.insertCurriculum", vo);
		
		return cnt;
	}
	
	public int updateCurriculum(CurriculumVO vo) {
		
		int cnt = update("curriculum.updateCurriculum", vo);
		
		return cnt;
	}
		
	public int deleteCurriculum(String id) {
		
		int cnt = delete("curriculum.deleteCurriculum",id);
		
		return cnt;
	}
}
