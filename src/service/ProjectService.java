package service;

import java.util.List;

import dao.BoardDAO;
import dao.BoardListDAO;
import dao.CodingTestDAO;
import dao.CommentDAO;
import dao.CurriculumDAO;
import dao.MemberDAO;
import dao.QuestionDAO;
import dao.SubjectDAO;
import vo.BoardListVO;
import vo.BoardVO;
import vo.CodingTestVO;
import vo.CommentVO;
import vo.CurriculumVO;
import vo.MemberVO;
import vo.QuestionVO;
import vo.SubjectVO;

public class ProjectService implements InterfaceService {
	

	private BoardDAO boardService;
	private BoardListDAO boardListService;
	private CodingTestDAO codingTestService;
	private CommentDAO commentService;
	private CurriculumDAO curriculumService;
	private MemberDAO memberService;
	private QuestionDAO questionService;
	private SubjectDAO subjectService;

	public ProjectService() {
		
		boardService = BoardDAO.getInstance();
		boardListService = BoardListDAO.getInstance();
		codingTestService = CodingTestDAO.getInstance();
		commentService = CommentDAO.getInstance();
		curriculumService = CurriculumDAO.getInstance();
		memberService = MemberDAO.getInstance();
		questionService = QuestionDAO.getInstance();
		subjectService = SubjectDAO.getInstance();
	}
	
	
	@Override
	public boolean insertMember(MemberVO vo) {

		int cnt = memberService.signUp(vo);

		if (cnt == 1) {
			return true;
		}

		return false;
	}

	@Override
	public boolean checkMember(String id) {

		int cnt = memberService.check(id);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public MemberVO selectMyMember(String id) {

		MemberVO vo = memberService.showMyData(id);

		return vo;
	}
	
	@Override
	public boolean updateMember(MemberVO vo) {

		int cnt = memberService.modify(vo);
		
		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePasswordMember(MemberVO vo) {
		int cnt = memberService.modifyPassword(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePhoneNumMember(MemberVO vo) {
		int cnt = memberService.modifyPhoneNum(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateActiveMember(MemberVO vo) {
		int cnt = memberService.accountStatement(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public int countAllMember() {

		int cnt = memberService.countAllMember();

		return cnt;
	}

	@Override
	public int countMember(MemberVO vo) {

		int cnt = memberService.countMember(vo);

		return cnt;
	}

	@Override
	public List<MemberVO> selectAllMember() {

		List<MemberVO> list = memberService.selectAllMember();

		return list;
	}

	@Override
	public List<MemberVO> selectAllMember(MemberVO vo) {

		List<MemberVO> list = memberService.selectAllMember(vo);

		return list;
	}

	@Override
	public QuestionVO selectQuestion(String id) {

		QuestionVO vo = questionService.searchQuestion(id);

		return vo;
	}

	@Override
	public boolean updateQuestion(QuestionVO vo) {

		int cnt = questionService.updateQuestion(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean insertQuestion(QuestionVO vo) {

		int cnt = questionService.insertQuestion(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<CurriculumVO> selectCurriculum() {

		List<CurriculumVO> list = curriculumService.selectCurriculum();

		return list;
	}
	
	@Override
	public CurriculumVO selectOneCurriculum(String id) {
		
		CurriculumVO vo = curriculumService.selectOneCurriculum(id);
		
		return vo;
	}


	@Override
	public boolean insertCurriculum(CurriculumVO vo) {

		int cnt = curriculumService.insertCurriculum(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateCurriculum(CurriculumVO vo) {

		int cnt = curriculumService.updateCurriculum(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCurriculum(String id) {

		int cnt = curriculumService.deleteCurriculum(id);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<BoardListVO> selectAllBL() {

		List<BoardListVO> list = boardListService.selectAllBoardList();

		return list;
	}
	
	@Override
	public BoardListVO selectBL(String id) {
		
		BoardListVO vo = boardListService.selectBoardList(id);
		
		return vo;
	}


	@Override
	public boolean checkBL(String id) {

		int cnt = boardListService.checkBoardList(id);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean insertBL(BoardListVO vo) {

		int cnt = boardListService.insertBoardList(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateBL(BoardListVO vo) {

		int cnt = boardListService.updateBoardList(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteBL(String id) {

		int cnt = boardListService.deleteBoardList(id);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<BoardVO> selectAllBoard() {

		List<BoardVO> list = boardService.selectAllBoard();

		return list;
	}

	@Override
	public List<BoardVO> selectAllBoard(BoardVO vo) {

		List<BoardVO> list = boardService.selectAllBoard(vo);

		return list;
	}

	@Override
	public BoardVO selectBoard(int no) {

		BoardVO vo = boardService.readBoard(no);

		return vo;
	}

	@Override
	public boolean insertBoard(BoardVO vo) {

		int cnt = boardService.insertBoard(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateBoard_Tit_Con(BoardVO vo) {

		int cnt = boardService.updateBoard(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateBoardTitle(BoardVO vo) {

		int cnt = boardService.updateBoardTitle(vo);
		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateBoardContent(BoardVO vo) {

		int cnt = boardService.updateBoardContent(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateBoardNotice(BoardVO vo) {

		int cnt = boardService.updateBoardIsNotice(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public int checkBoard(int boardNo) {

		int cnt = boardService.checkBoard(boardNo);

		return cnt;
	}

	@Override
	public boolean deleteBoard(int boardNo) {

		int cnt = boardService.deleteBoard(boardNo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<CommentVO> selectAllComment(int boardNo) {

		List<CommentVO> list = commentService.selectAllComment(boardNo);

		return list;
	}

	@Override
	public CommentVO selectComment(CommentVO cv) {

		CommentVO vo = commentService.selectComment(cv);

		return vo;
	}

	@Override
	public boolean insertComment(CommentVO vo) {

		int cnt = commentService.insertComment(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateComment(CommentVO vo) {
		
		int cnt = commentService.updateComment(vo);

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public int checkComment(int commentNo) {
		
		int cnt = commentService.checkComment(commentNo);
		
		return cnt;
	}

	@Override
	public boolean deleteComment(int commentNo) {
		
		int cnt = commentService.deleteComment(commentNo);
		
		if(cnt==1) {
			return true;
		}
		return false;
	}

	@Override
	public List<CodingTestVO> selectAllCodingTest() {
		
		List<CodingTestVO> list = codingTestService.selectAllCoding();
		
		return list;
	}

	@Override
	public List<CodingTestVO> selectCodingTest(CodingTestVO vo) {
		
		List<CodingTestVO> list = codingTestService.selectCoding(vo);
		
		return list;
	}

	@Override
	public CodingTestVO selectOneCodingTest(int no) {
		
		CodingTestVO vo = codingTestService.selectOneCoding(no);
		
		return vo;
	}

	@Override
	public int checkCodingTest(CodingTestVO vo) {
		
		int cnt = codingTestService.countCoding(vo);
		
		return cnt;
	}

	@Override
	public boolean insertCodingTest(CodingTestVO vo) {
		
		int cnt = codingTestService.insertCoding(vo);
		
		if(cnt==1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateCodingTest(CodingTestVO vo) {
		
		int cnt = codingTestService.updateCoding(vo);
		
		if(cnt==1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCodingTest(int no) {
		
		int cnt = codingTestService.deleteCoding(no);
		
		if(cnt==1) {
			return true;
		}
		return false;
	}

	@Override
	public int checkSubject(String id) {
		
		int cnt = subjectService.checkSubject(id);
		
		return cnt;
	}

	@Override
	public List<SubjectVO> selectAllSubject() {
		
		List<SubjectVO> list = subjectService.selectAllSubject();
		
		return list;
	}

	@Override
	public List<SubjectVO> selectSubject(SubjectVO vo) {
		
		List<SubjectVO> list = subjectService.selectSubject(vo);
		
		return list;
	}

	@Override
	public SubjectVO selectOneSubject(String id) {
		
		SubjectVO vo = subjectService.selectOneSubject(id);
		
		return vo;
	}

	@Override
	public int insertSubject(SubjectVO vo) {
		
		int cnt = subjectService.insertSubject(vo);
		
		return cnt;
	}

	@Override
	public int deleteSubject(String id) {
		
		int cnt = subjectService.deleteSubject(id);
		
		return cnt;
	}

	@Override
	public int updateSubject(SubjectVO vo) {
		
		int cnt = subjectService.updateSubject(vo);
		
		return cnt;
	}


	
	


	
}
