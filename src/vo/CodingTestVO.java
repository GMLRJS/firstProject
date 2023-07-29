package vo;

import java.util.Date;

public class CodingTestVO {

	private int problemNo;
	private String levell;
	private String codingContent;
	private String answer;
	private String subjectID;
	private String titleContent;
	private String title;
	private Date date;
	
	
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
	public String getTitleContent() {
		return titleContent;
	}
	public void setTitleContent(String titleContent) {
		this.titleContent = titleContent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	
}
