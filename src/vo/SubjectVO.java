package vo;

public class SubjectVO {
	
	
	private String subjectID; // 과목 코드
	private String subjectName; // 과목 이름
	
	public String getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	@Override
	public String toString() {
		return "SubjectVO [subjectID=" + subjectID + ", subjectName=" + subjectName + "]";
	}
	
	
	
	
	
	
	
}
