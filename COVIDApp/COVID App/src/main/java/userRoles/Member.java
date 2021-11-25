package userRoles;

public class Member extends UserRole {
	
	public Member() {
		super.roleName = "member";
		super.roleID = 3;
		seeResults = new CanNotSeeResults();
		editTeam = new CanNotEditTeam();
		editQuestions = new CanNotEditQuestions();
	}
}
