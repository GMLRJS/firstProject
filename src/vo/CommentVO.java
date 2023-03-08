package vo;

public class CommentVO {

	private int commentNo;
	private String memberID;
	private String commentContent;
	private String date;
	private int boardNo;
	
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	@Override
	public String toString() {
		return "CommentVO [commentNo=" + commentNo + ", memberID=" + memberID + ", commentContent=" + commentContent
				+ ", date=" + date + ", boardNo=" + boardNo + "]";
	}
}
