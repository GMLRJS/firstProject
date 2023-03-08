package vo;

import java.util.Date;

public class CodingTestVO {

	private int problemNo;
	private String title;
	private String levell;
	private String codingContent;
	private String answer;
	private String subjectID;
	private String date;
	
	
	public int getProblemNo() {
		return problemNo;
	}
	public void setProblemNo(int problemNo) {
		this.problemNo = problemNo;
	}
	public String getLevell() {
		return levell;
	}
	public void setLevell(String levell) {
		this.levell = levell;
	}
	public String getCodingContent() {
		return codingContent;
	}
	public void setCodingContent(String codingContent) {
		this.codingContent = codingContent;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	
}
