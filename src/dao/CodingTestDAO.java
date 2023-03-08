package dao;

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

	public int countCoding(CodingTestVO vo) {

		int cnt = selectOne("coding.checkCoding", vo);

		return cnt;

	}

	public int updateCoding(CodingTestVO vo) {

		int cnt = update("coding.updateCoding", vo);

		return cnt;
	}

	public int insertCoding(CodingTestVO vo) {

		int cnt = insert("coding.insertCoding", vo);

		return cnt;
	}

	public int deleteCoding(int no) {

		int cnt = delete("coding.deleteCoding", no);

		return cnt;
	}

	public List<CodingTestVO> selectAllCoding() {

		List<CodingTestVO> list = selectList("coding.selectAllCoding");

		return list;
	}

	public List<CodingTestVO> selectCoding(CodingTestVO vo) {

		List<CodingTestVO> list = selectList("coding.selectCoding", vo);

		return list;
	}
	
	public CodingTestVO selectOneCoding(int id) {
		
		CodingTestVO vo = selectOne("coding.selectOneCoding", id);
		
		return vo;
	}

}
