package service;

import java.util.List;

import vo.BoardListVO;
import vo.BoardVO;
import vo.CodingTestVO;
import vo.CommentVO;
import vo.CurriculumVO;
import vo.MemberVO;
import vo.QuestionVO;
import vo.SubjectVO;

public interface InterfaceService {
	
	
	//============================================MemberDAO==================================================
	/**
	 * 회원 가입 메소드
	 * @param vo 회원가입시 필요한 정보를 담은 vo객체
	 * @return 리턴 타입은 boolean
	 */
	public boolean insertMember(MemberVO vo);
	
	/**
	 * 회원가입 혹은 계정찾기 등 계정의 ID를 입력받아 그 계정이 있는지 확인하는 메소드
	 * @param vo 계정의 ID
	 * @return 리턴 타입은 boolean
	 */
	public boolean checkMember(String id);
	
	
	/**
	 * 자기 자신의 정보를 조회할때 사용할 메소드
	 * (마이페이지 등에서 정보 수정이나 게시물 확인시 사용예정)
	 * @param vo 계정의 ID
	 * @return 회원가입시 입력했던 정보가 담겨있는 vo객체가 리턴된다.
	 */
	public MemberVO selectMyMember(String id);
	
	public boolean updateMember(MemberVO vo);
	
	/**
	 * 비밀번호를 수정하는 메소드
	 * @param vo 계정 ID와 비밀번호가 담긴 vo객체
	 * @return 리턴 타입은 boolean
	 */
	public boolean updatePasswordMember(MemberVO vo);
	
	/**
	 * 전화번호를 수정하는 메소드
	 * @param vo 계정 ID, 비밀번호 그리고 수정할 휴대폰 번호가 담긴 vo 객체
	 * @return 리턴 타입은 boolean
	 */
	public boolean updatePhoneNumMember(MemberVO vo);
	
	/**
	 * 계정 활성화 여부를 수정하는 메소드 (기본 디폴트는 우선 N)
	 * @param vo 계정 ID와 수정할 계정 활성화 여부(Y,N)가 담긴 vo 객체
	 * @return 리턴 타입은 boolean
	 */
	public boolean updateActiveMember(MemberVO vo);
	
	/**
	 * 활성화 되어있는 전체 가입자 수를 조회하는 메소드
	 * @return 활성화 되어있는 계정의 숫자를 리턴받는다.
	 */
	public int countAllMember();
	
	/**
	 * 특정 조건의 해당되는 가입자의 수를 알 수 있는 메소드
	 * 회원가입시 입력했던 정보 중 원하는 정보 아무거나 입력 가능
	 * @param vo 검색 조건을 담은 vo 객체
	 * @return 검색 조건에 해당하는 계정 수
	 */
	public int countMember(MemberVO vo);
	
	/**
	 * 활성화된 계정의 모든 정보를 가져오는 메소드
	 * @return MemberVO객체를 담은 List타입으로 반환
	 */
	public List<MemberVO> selectAllMember();

	/**
	 * 특정 조건에 해당하는 가입자와 그 정보를 가져오는 메소드
	 * @param vo 입력받은 특정 조건이 담긴 vo 객체
	 * @return MemberVO객체를 담은 List타입으로 반환
	 */
	public List<MemberVO> selectAllMember(MemberVO vo);
	
	//==========================================QuestionDAO====================================================
	
	/**
	 * 계정 찾기시 필요한 질문을 가져오는 메소드
	 * @param vo  해당 계정의 계정 ID
	 * @return 계정ID, 질문 내용, 정답이 담긴 vo객체를 반환
	 */
	public QuestionVO selectQuestion(String id);
	
	/**
	 * 계정 생성시 입력받은 질문과 답을 수정하는 메소드 (마이페이지 정보 수정)
	 * @param vo 해당 계정 ID, 질문 내용, 정답이 담긴 vo 객체
	 * @return 리턴 타입은 boolean
	 */
	public boolean updateQuestion(QuestionVO vo);
	
	/**
	 * 계정 생성시 입력받은 질문과 답을 계정 ID와 함께 등록하는 메소드
	 * @param vo 해당 계정 ID, 질문 내용, 정답이 담긴 vo 객체
	 * @return 리턴 타입은 boolean
	 */
	public boolean insertQuestion(QuestionVO vo);
	
	//==========================================CurriculumDAO===========================================
	
	/**
	 * 전체 커리큘럼을 조회하는 메소드 
	 * @return 커리큘럼 정보가 담긴 vo객체 리스트
	 */
	public List<CurriculumVO> selectCurriculum();
	
	/**
	 * 과정 하나를 조회하는 메소드
	 * @param id  과정 ID
	 * @return 커리큘럼 정보가 담긴 vo객체 리스트
	 */
	public CurriculumVO selectOneCurriculum(String id);
	
	/**
	 * 커리큘럼을 등록하는 메소드
	 * @param vo 커리큘럼의 ID, 이름, 시작날짜, 수료날짜가 담긴 vo객체
	 * @return 리턴 타입은 boolean
	 */
	public boolean insertCurriculum(CurriculumVO vo);
	
	/**
	 * 커리큘럼을 수정하는 메소드
	 * @param vo 커리큘럼의 ID, 이름, 시작날짜, 수료날짜가 담긴 vo객체
	 * @return 리턴 타입은 booelan
	 */
	public boolean updateCurriculum(CurriculumVO vo);
	
	/**
	 * 커리큘럼을 삭제하는 메소드
	 * @param vo 커리큘럼 ID
	 * @return 리턴 타입은 boolean
	 */
	public boolean deleteCurriculum(String id);
	
	//=========================================BoardListDAO============================================
	
	/**
	 * 게시판 목록을 전체 조회하는 메소드
	 * @return 게시판 ID, 게시판 이름의 vo 객체가 담긴 list
	 */
	public List<BoardListVO> selectAllBL();
	
	public BoardListVO selectBL(String id);
	
	/**
	 * 게시판 id를 입력받아 존재하는지 조회하는 메소드
	 * @param id 게시판 id
	 * @return 리턴값은 boolean
	 */
	public boolean checkBL(String id);
	
	/**
	 * 게시판 등록 메소드
	 * @param vo  게시판 ID, 게시판이름이 담긴 vo 객체
	 * @return 리턴값은 boolean
	 */
	
	public boolean insertBL(BoardListVO vo);
	
	/**
	 * 게시판 수정 메소드
	 * @param vo  게시판 ID, 게시판이름이 담긴 vo 객체
	 * @return 리턴값은 boolean
	 */
	public boolean updateBL(BoardListVO vo);
	
	/**
	 * 게시판 삭제 메소드
	 * @param id  게시판 id
	 * @return 리턴값은 boolean
	 */
	public boolean deleteBL(String id);
	
	//========================================BoardDAO===================================================
	
	/**
	 * 모든 게시글의 정보를 가져오는 메소드
	 * @return 게시글의 모든 정보가 담긴 vo객체 list
	 */
	public List<BoardVO> selectAllBoard();
	
	/**
	 * 특정 조건의 게시글 정보를 가져오는 메소드
	 * @param vo 입력받은 조건이 담긴 vo 객체
	 * @return 특정 게시글의 모든 정보가 담긴 vo객체 list
	 */
	public List<BoardVO> selectAllBoard(BoardVO vo);
	
	/**
	 *  게시글 ID를 이용해 단 하나의 게시글을 가져오는 메소드
	 * @param boardNo 게시글 boardNo
	 * @return 하나의 게시글 vo 객체
	 */
	public BoardVO selectBoard(int boardNo);
	

	
	/**
	 * 게시글을 업로드 하는 메소드
	 * @param vo 게시글 정보가 모두 담긴 vo객체
	 * @return 업로드가 성공하면 1, 실패하면 0을 리턴한다.
	 */
	public boolean insertBoard(BoardVO vo);
	
	/**
	 * 게시물의 제목과 내용을 수정하는 메소드
	 * @param vo 수정된 게시물의 정보가 담긴 vo객체 (제목, 내용)
	 * @return 업로드가 성공하면 1, 실패하면 0을 리턴한다.
	 */
	public boolean updateBoard_Tit_Con(BoardVO vo);
	
	/**
	 * 게시물의 제목을 수정하는 메소드
	 * @param vo 수정된 게시물의 정보가 담긴 vo객체(제목)
	 * @return 업로드가 성공하면 1, 실패하면 0을 리턴한다.
	 */
	public boolean updateBoardTitle(BoardVO vo);
	
	/**
	 * 게시물의 내용을 수정하는 메소드
	 * @param vo 수정된 게시물의 정보가 담긴 vo객체(내용)
	 * @return 업로드가 성공하면 1, 실패하면 0을 리턴한다.
	 */
	public boolean updateBoardContent(BoardVO vo);
	
	/**
	 * 게시물의 공지사항 여부를 수정하는 메소드
	 * @param vo 수정된 게시물의 정보가 담긴 vo객체(공지사항)
	 * @return 업로드가 성공하면 1, 실패하면 0을 리턴한다.
	 */
	public boolean updateBoardNotice(BoardVO vo);
	
	/**
	 * 게시물이 있는지 확인하는 메소드
	 * @param boardNo 게시글의 번호를 입력받는다.
	 * @return 게시글이 있으면 1, 없으면 0을 리턴한다.
	 */
	public int checkBoard(int boardNo);
	
	/**
	 * 게시물을 삭제하는 메소드
	 * @param boardNo 삭제할 게시글의 번호를 입력받는다.
	 * @return 삭제가 성공하면 1, 실패하면 0을 리턴한다.
	 */
	public boolean deleteBoard(int boardNo);
	
	//=============================================CommentDAO============================================
	
	/**
	 * 특정 게시물의 모든 댓글을 보여주는 메소드
	 * @param boardNo  게시글 번호를 입력받는다.
	 * @return 댓글정보가 담긴 vo객체 list
	 */
	public List<CommentVO> selectAllComment(int boardNo);
	
	/**
	 * 특정 댓글의 정보를 보여주는 메소드
	 * @param commentNo 댓글번호를 입력받는다.
	 * @return 댓글정보가 담긴 vo
	 */
	public CommentVO selectComment(CommentVO vo);
	
	/**
	 * 댓글을 입력하는 메소드
	 * @param vo 댓글정보가 담긴 vo 객체
	 * @return  댓글 업로드가 성공하면 1, 실패하면 0을 리턴한다.
	 */
	public boolean insertComment(CommentVO vo);
	
	/**
	 * 댓글을 수정하는 메소드
	 * @param vo 댓글정보가 담긴 vo 객체
	 * @return  댓글 업로드가 성공하면 1, 실패하면 0을 리턴한다.
	 */
	public boolean updateComment(CommentVO vo);
	
	/**
	 * 댓글이 있는지 확인하는  메소드
	 * @param CommentNo  댓글번호를 입력받는다.
	 * @return 댓글이 존재하면 1, 존재하지 않으면 0을 반환
	 */
	public int checkComment(int commentNo);
	
	/**
	 * 댓글을 삭제하는 메소드
	 * @param commentNo
	 * @return 댓글 삭제가 성공하면 1, 실패하면 0을 리턴한다.
	 */
	public boolean deleteComment(int commentNo);
	
	//==========================================CodingTestDAO===================================================
	
	/**
	 * 코딩 문제 전체를 조회하는 메소드
	 * @return codingTest정보가 모두 담긴 vo 리스트
	 */
	public List<CodingTestVO> selectAllCodingTest();
	
	/**
	 * 코딩 문제 중 특정 조건의 문제만 조회하는 메소드  
	 * @param vo 원하는 검색 조건을 담은 vo 객체
	 * @return 해당 조건에 해당하는 vo 객체 리스트
	 */
	public List<CodingTestVO> selectCodingTest(CodingTestVO vo);
	
	/**
	 * 단 하나의 코딩 문제만 조회하는 메소드
	 * @param no  문제의 번호를 입력받는다
	 * @return 입력받은 문제 번호의 해당하는 문제 
	 */
	public CodingTestVO selectOneCodingTest(int no);
	
	/**
	 * 특정 조건의 코딩 문제의 갯수를 조회하는 메소드 
	 * @param vo  특정 코딩 문제 조건이 담긴 vo 객체
	 * @return 문제 갯수
	 */
	public int checkCodingTest(CodingTestVO vo);
	
	/**
	 * 코딩 문제를 등록하는 메소드
	 * @param vo  코딩문제의 정보가 담긴 vo 객체
	 * @return 새로운 문제 등록이 성공하면 1, 실패시 0 반환
	 */
	public boolean insertCodingTest(CodingTestVO vo);
	
	/**
	 * 코딩 문제를 수정하는 메소드
	 * @param vo 수정한 코딩문제의 정보가 담긴 vo 객체
	 * @return 문제가 잘 수정되면 1, 실패하면 0 반환
	 */
	public boolean updateCodingTest(CodingTestVO vo);
	
	/**
	 * 코딩 문제를 삭제하는 메소드
	 * @param no  삭제하고 싶은 코딩문제의 문제 번호
	 * @return  문제가 잘 삭제되면 1, 실패하면 0 반환
	 */
	public boolean deleteCodingTest(int no);
	
	
	//============================================SubjectDAO=======================================================
	
	
	public int checkSubject(String id);
	
	
	public List<SubjectVO> selectAllSubject();
	
	
	public List<SubjectVO> selectSubject(SubjectVO vo);
	
	
	public SubjectVO selectOneSubject(String id);
	
	
	public int insertSubject(SubjectVO vo);
	
	
	public int deleteSubject(String id);
	
	
	public int updateSubject(SubjectVO vo);
	
}
