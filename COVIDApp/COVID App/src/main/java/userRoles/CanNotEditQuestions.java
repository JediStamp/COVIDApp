package userRoles;

public class CanNotEditQuestions implements EditQuestionBehaviour {

	@Override
	public String editQuestions() {
//		System.out.println("I can not edit the questions");
		return "";
	}

}
