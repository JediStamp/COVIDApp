package beans.userRoles;

public class CanEditQuestions implements EditQuestionBehaviour {

	@Override
	public String editQuestions() {
//		System.out.println("I can edit the questions");
		return "<a href=\"question.jsp\">QUESTION</a>";
	}

}
