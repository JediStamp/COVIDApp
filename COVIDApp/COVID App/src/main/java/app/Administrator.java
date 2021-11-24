package app;

public class Administrator extends UserRole{

	public Administrator() {
		super.role = "admin";
		super.roleID = 2;
		super.canSeeResults = true;
		super.canEditTeam = false;
	}
}
