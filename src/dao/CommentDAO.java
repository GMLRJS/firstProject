package dao;

import java.util.List;

import mybatisDAO.MyBatisDAO;
import vo.CommentVO;

public class CommentDAO extends MyBatisDAO {

	private static CommentDAO commentDao;
	
	private CommentDAO() {
	
	}
	
	public static CommentDAO getInstance() {
		if (commentDao == null) {
			commentDao = new CommentDAO();
		}
		return commentDao;
	}
	
	public int insertComment(CommentVO cv) {
		
		int cnt = insert("comment.insertComment", cv);

		return cnt;
	}
	
	public int updateComment(CommentVO cv) {
		
		int cnt = update("comment.updateComment", cv);
		
		return cnt;
	}
	
	public int checkComment(int CommentNo) {
		
		
		
		int cnt = selectOne("comment.checkComment", CommentNo);
		
		return cnt;
	}
	
	public int deleteComment(int CommentNo) {
		
		int cnt = delete("comment.deleteComment", CommentNo);
		
		return cnt;
	}
	
	// 게시글 번호에 해당하는 모든 댓글을 보여주는 기능
	public List<CommentVO> selectAllComment(int boardNo) {
		
		List<CommentVO> CommentList = selectList("comment.selectAllComment", boardNo);
		
		return CommentList;
	}
	
	public CommentVO selectComment(int commentNo) {
		
		CommentVO vo = selectOne("commeny.selectComment", commentNo);
		
		return vo;
	}
}






