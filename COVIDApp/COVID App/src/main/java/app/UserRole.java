package app;

public abstract class UserRole {
	protected String role;
	protected int roleID;
	protected boolean canSeeResults;
	protected boolean canEditTeam;
	
	public boolean seeResults() {
		return canSeeResults;
	}
	
	public boolean editTeam() {
		return canEditTeam;
	}
	

}
