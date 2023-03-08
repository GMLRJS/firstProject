package dao;

import mybatisDAO.MyBatisDAO;
import vo.QuestionVO;

public class QuestionDAO extends MyBatisDAO {

	private static QuestionDAO questionDAO;

	private QuestionDAO() {
	}

	public static QuestionDAO getInstance() {

		if (questionDAO == null) {
			questionDAO = new QuestionDAO();
		}
		return questionDAO;
	}

	public QuestionVO searchQuestion(String id) {

		QuestionVO myVo = selectOne("question.selectQuestion", id);

		return myVo;
	}

	public int updateQuestion(QuestionVO vo) {

		int cnt = update("question.updateQuestion", vo);

		return cnt;
	}

	public int insertQuestion(QuestionVO vo) {

		int cnt = insert("question.insertQuestion", vo);

		return cnt;

	}

}
