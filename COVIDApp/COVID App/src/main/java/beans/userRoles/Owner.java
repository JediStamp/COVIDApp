package beans.userRoles;

public class Owner extends UserRole {
	
	public Owner() {
		super.roleName = "owner";
		super.roleID = 1;
		seeResults = new CanSeeResults();
		editTeam = new CanEditTeam();
		editQuestions = new CanEditQuestions();
	}

}
