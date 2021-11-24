package app;

public class Member extends UserRole {
	
	public Member() {
		super.role = "member";
		super.roleID = 3;
		super.canSeeResults = false;
		super.canEditTeam = false;
	}
}
