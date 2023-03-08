package vo;

public class MemberVO {

	private String memberID;
	private String password;
	private String name;
	private String gender;
	private String phoneNum;
	private String birth;
	private String isActive;
	private String curriculumID;
	
	
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String isActive() {
		return isActive;
	}
	public void setActive(String isActive) {
		this.isActive = isActive;
	}
	public String getCurriculumID() {
		return curriculumID;
	}
	public void setCurriculumID(String curriculumID) {
		this.curriculumID = curriculumID;
	}
	
	@Override
	public String toString() {
		return "MemberVO [memberID=" + memberID + ", password=" + password + ", name=" + name + ", gender=" + gender
				+ ", phoneNum=" + phoneNum + ", birth=" + birth + ", isActive=" + isActive + ", curriculumID="
				+ curriculumID + "]";
	}
}
