package beans.questionnaires;

import java.sql.Timestamp;

public class QuestionAnswer {
	private String userID;
	private String fName;
	private String lName;
	private int teamID;
	private int eventID;
	private int questionID;
	private int answerID;
	private int isRightAns;
	private Timestamp timestamp;
	
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
	public int getRightAns() {
		return isRightAns;
	}
	public void setRightAns(int isRightAns) {
		this.isRightAns = isRightAns;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
