package userRoles;

public abstract class UserRole {
	protected String roleName;
	protected int roleID;
	
	SeeResultsBehaviour seeResults;
	EditTeamBehaviour editTeam;
	EditQuestionBehaviour editQuestions;
	
	public void setSeeResultsBehaviour() {

	}
	
	public void editTeamBehaviour() {

	}
	
	public String seeResultsPage() {
		return seeResults.seeResults();
	}
	
	public String seeTeamPage() {
		return editTeam.editTeam();
	}
	
	public String seeQuestionPage() {
		return editQuestions.editQuestions();
	}
	
	public int getRoleID() {
		return roleID;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public static UserRole getRoleFromID(int ID) {
		UserRole role;
		switch(ID) {
			case 1:
				role = new Owner();
				break;
			case 2:
				role = new Administrator();
				break;
			case 3:
				role = new Member();
				break;
			default:
				role = new Member();
		}
		return role;
	}

}
