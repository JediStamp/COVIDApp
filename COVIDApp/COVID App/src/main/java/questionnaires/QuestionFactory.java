package questionnaires;

import java.util.List;

public class QuestionFactory {
	
	public static Question getNewQuestion (boolean newQuestion, int questionID, String question, List<List> possibleAnswers, int goAnswer) {
		
		if(newQuestion) {
			
			//create new question id (+1) question, possibleAnswers, and goAnswer
			questionID = questionID + 1;
			possibleAnswers.add(possibleAnswers);
			goAnswer = 1;
			
			System.out.println("created new question");
			
			
		}else {

			//update new question id, possible answers, and goAnswer
			
			System.out.println("updated old question");
			
		}
		
		return null;
		
		}
	}

