package vo;

public class QuestionVO {
	
	private String memberID;
	private String question;
	private String answer;
	
	
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Override
	public String toString() {
		return "QuestionVO [memberID=" + memberID + ", question=" + question + ", answer=" + answer + "]";
	}
}
