package app;

public abstract class UserRole {
	protected String role;
	protected int roleID;
	protected boolean canSeeResults;
	protected boolean canEditTeam;
	
	public boolean seeResultsBehaviour() {
		return canSeeResults;
	}
	
	public boolean editTeamBehaviour() {
		return canEditTeam;
	}
	

}
