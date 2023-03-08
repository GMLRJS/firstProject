package vo;

public class BoardListVO {
	
	private String boardID;
	private String boardName;
	
	
	public String getBoardID() {
		return boardID;
	}
	public void setBoardID(String boardID) {
		this.boardID = boardID;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
	@Override
	public String toString() {
		return "BoardListVO [boardID=" + boardID + ", boardName=" + boardName + "]";
	}
}
