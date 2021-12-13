package beans.questionnaires;

import java.util.List;

public class Question {
	private int questionID;
	private String question;
	private List<String> possibleAnswers;
	private int goAnswer;
	
	public Question(String question, List<String> possibleAnswers, int goAnswer) {
		this.question = question;
		this.possibleAnswers = possibleAnswers;
		this.goAnswer = goAnswer;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getPossibleAnswers() {
		return possibleAnswers;
	}

	public void setPossibleAnswers(List<String> possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}

	public int getGoAnswer() {
		return goAnswer;
	}

	public void setGoAnswer(int goAnswer) {
		this.goAnswer = goAnswer;
	}
}
