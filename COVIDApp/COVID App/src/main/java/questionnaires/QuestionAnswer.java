package questionnaires;

public class QuestionAnswer {
	private int questionID;
	private int answerID;
	private boolean isRightAns;
	
	public QuestionAnswer(int questionID, int answerID) {
		this.questionID = questionID;
		this.answerID = answerID;
	}
	
	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public int getAnswerID() {
		return answerID;
	}
	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}
	public boolean isRightAns() {
		return isRightAns;
	}
	public void setRightAns(boolean isRightAns) {
		this.isRightAns = isRightAns;
	}
}
