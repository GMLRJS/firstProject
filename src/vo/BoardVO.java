package vo;

import java.util.Date;

public class BoardVO {
	
	private int boardNo;
	private String title;
	private String memberID;
	private String writer;
	private String date;       // SYSDATE
	private String isNotice;  // Y or N
	private int hits;
	private String content;
	private String boardID;
	
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String isNotice() {
		return isNotice;
	}
	public void setIsNotice(String isNotice) {
		this.isNotice = isNotice;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBoardID() {
		return boardID;
	}
	public void setBoardID(String boardID) {
		this.boardID = boardID;
	}
	
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", title=" + title + ", memberID=" + memberID + ", writer=" + writer
				+ ", date=" + date + ", isNotice=" + isNotice + ", hits=" + hits + ", content=" + content
				+ ", boardCode=" + boardID + "]";
	}
	
	
}
