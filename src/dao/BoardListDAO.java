package dao;

import java.util.List;

import mybatisDAO.MyBatisDAO;
import vo.BoardListVO;

public class BoardListDAO extends MyBatisDAO {

	private static BoardListDAO boardListDao;
	
	private BoardListDAO() {
		
	}
	
	public static BoardListDAO getInstance() {
		if (boardListDao == null) {
			boardListDao = new BoardListDAO();
		}
		return boardListDao;
	}
	
	public int insertBoardList(BoardListVO bv) {
		
		int cnt = insert("boardList.insertBoardList", bv);

		return cnt;
	}
	
	public int updateBoardList(BoardListVO bv) {
		
		int cnt = update("boardList.updateBoardList", bv);
		
		return cnt;
	}
	
	public int checkBoardList(String boardID) {
		
		
		int cnt = selectOne("boardList.checkBoardList", boardID);
		
		return cnt;
	}
	
	public int deleteBoardList(String boardID) {
		
		int cnt = delete("boardList.deleteBoardList", boardID);
		
		return cnt;
	}
	
	public List<BoardListVO> selectAllBoardList() {
		
		List<BoardListVO> boardList = selectList("boardList.selectAllBoardList");
		
		return boardList;
	}
	
	public BoardListVO selectBoardList(String id) {
		
		BoardListVO vo = selectOne("boardList.selectBoardList", id);
		
		return vo;
	}
}
