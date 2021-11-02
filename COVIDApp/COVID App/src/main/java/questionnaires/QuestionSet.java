package questionnaires;

import java.util.ArrayList;

public class QuestionSet {
	private ArrayList<Question> questions;
	private int teamID;
	
	public QuestionSet(ArrayList<Question> questions, int teamId) {
		this.teamID = teamId;
		this.questions = questions;
	}
	
	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
}
