package dao;

import java.util.List;
import mybatisDAO.MyBatisDAO;
import vo.BoardVO;

public class BoardDAO extends MyBatisDAO {

	private static BoardDAO boardDao;
	
	private BoardDAO() {
		
	}
	
	public static BoardDAO getInstance() {
		if (boardDao == null) {
			boardDao = new BoardDAO();
		}
		return boardDao;
	}
	
	public int insertBoard(BoardVO bv) {
		
		int cnt = insert("board.insertBoard", bv);

		return cnt;
	}
	
	
	// 게시글 제목 + 내용 수정
	public int updateBoard(BoardVO bv) {
		
		int cnt = update("board.updateBoard", bv);
		
		return cnt;
	}

	// 게시글 제목 수정
	public int updateBoardTitle(BoardVO bv) {
		
		int cnt = update("board.updateBoardTitle", bv);
		
		return cnt;
	}

	// 게시글 내용 수정
	public int updateBoardContent(BoardVO bv) {
		
		int cnt = update("board.updateBoardContent", bv);
		
		return cnt;
	}

	// 게시글 공지사항 여부 수정
	public int updateBoardIsNotice(BoardVO bv) {
		
		int cnt = update("board.updateBoardIsNotice", bv);
		
		return cnt;
	}
	
	
	public int checkBoard(int boardNo) {
		
		
		
		int cnt = selectOne("board.checkBoard", boardNo);
		
		return cnt;
	}
	
	public int deleteBoard(int boardNo) {
		
		int cnt = delete("board.deleteBoard", boardNo);
		
		return cnt;
	}
	
	// 모든 게시글 목록 가져오기
	public List<BoardVO> selectAllBoard() {
		
		List<BoardVO> boardList = selectList("board.selectAllBoard");
		
		return boardList;
	}
	
	// 특정 게시판의 게시글 가져오기
	public List<BoardVO> selectAllBoard(BoardVO bv) {
		
		List<BoardVO> boardList = selectList("board.selectAllBoard", bv);
		
		return boardList;
	}
	
	
	// 조회수 증가 (업데이트)
	public void updateHits(int boardNo) {
		
		update("updateBoardHits", boardNo);
	}
	
	// 게시글 읽기 기능 ( + 조회수 증가 )
	public BoardVO readBoard(int boardNo) {
		
		BoardVO resultVO = selectOne("board.readBoard", boardNo);
		
		updateHits(boardNo);  // 조회수 증가
		
		return resultVO;
	}
}






