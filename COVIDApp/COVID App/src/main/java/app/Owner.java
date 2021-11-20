package app;

public class Owner extends UserRole {
	
	public Owner() {
		super.role = "owner";
		super.roleID = 1;
		super.canSeeResults = true;
		super.canEditTeam = true;
	}

}
