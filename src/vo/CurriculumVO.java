package vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurriculumVO {

	private String curriculumID;
	private String curriculumName;
	private String startDate;
	private String endDate;
	
	
	public String getCurriculumID() {
		return curriculumID;
	}
	public void setCurriculumID(String curriculumID) {
		this.curriculumID = curriculumID;
	}
	public String getCurriculumName() {
		return curriculumName;
	}
	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "CurriculumVO [curriculumID=" + curriculumID + ", curriculumName=" + curriculumName + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
}
