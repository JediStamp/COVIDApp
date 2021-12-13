package beans.questionnaires;

import java.util.ArrayList;

public class QuestionSet {
	private ArrayList<QuestionAnswer> questions;
	private int teamID;
	private int eventID;
	
	public QuestionSet(int teamID, int eventID) {
		this.teamID = teamID;
		this.setEventID(eventID);
		this.questions = new ArrayList<QuestionAnswer>();
	}
	
	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	public ArrayList<QuestionAnswer> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<QuestionAnswer> questions) {
		this.questions = questions;
	}
	
	public void addQuestionAnswer(QuestionAnswer qa) {
		questions.add(qa);
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	
}
