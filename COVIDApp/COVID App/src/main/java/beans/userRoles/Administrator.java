package beans.userRoles;

public class Administrator extends UserRole{

	public Administrator() {
		super.roleName = "admin";
		super.roleID = 2;
		seeResults = new CanSeeResults();
		editTeam = new CanNotEditTeam();
		editQuestions = new CanNotEditQuestions();
	}
}
