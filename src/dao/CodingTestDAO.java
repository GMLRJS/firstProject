package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import mybatisDAO.MyBatisDAO;
import vo.CodingTestVO;

public class CodingTestDAO extends MyBatisDAO {

	private static CodingTestDAO coding;

	private CodingTestDAO() {

	}

	public static CodingTestDAO getInstance() {
		if (coding == null) {
			coding = new CodingTestDAO();
		}
		return coding;
	}

	public int insertCoding(CodingTestVO pv) {

		int cnt = insert("coding.insertCoding", pv);

		return cnt;
	}

	public int updateCoding(CodingTestVO pv) {

		int cnt = update("coding.updateCoding", pv);

		return cnt;
	}

	public boolean checkCoding(int problemNo) {

		boolean chk = false;

		int cnt = selectOne("coding.checkCoding", problemNo);

		if (cnt > 0) {

			chk = true;
		}

		return chk;
	}

	public int deleteCoding(int problemNo) {

		int cnt = delete("coding.deleteCoding", problemNo);

		return cnt;
	}

	public List<CodingTestVO> selectAllCoding() {

		List<CodingTestVO> codingList = selectList("coding.selectAllCoding");

		return codingList;
	}

	public List<CodingTestVO> searchCoding(CodingTestVO vo) {

		List<CodingTestVO> codingList = selectList("coding.searchCoding", vo);

		return codingList;
	}

	// 과목 키워드 입력시
	public List<CodingTestVO> titleCheck(CodingTestVO vo) {

		List<CodingTestVO> codingList = selectList("coding.titleCheck",vo);

		return codingList;
	}

	// 문제 난이도 입력시
	public List<CodingTestVO> searchLevel(CodingTestVO vo) {

		List<CodingTestVO> codingList = selectList("coding.searchLevel",vo);

		return codingList;
	}

	public List<CodingTestVO> selectCoding(CodingTestVO vo) {

		List<CodingTestVO> codingTestList = selectList("coding.selectAllCodingTest",vo);

		return codingTestList;
	}

	public List<CodingTestVO> choiceAnswer(CodingTestVO pv) {

		List<CodingTestVO> codingList = selectList("coding.choiceAnswer",pv);

		return codingList;
	}
	
	public CodingTestVO selectOneCoding(int id) {
		
		CodingTestVO vo = selectOne("coding.selectOneCoding", id);
		
		return vo;
	}
	
	public int countCoding(CodingTestVO vo) {

		int cnt = selectOne("coding.checkCoding", vo);

		return cnt;

	}
}
