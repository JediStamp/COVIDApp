package userRoles;

public class CanNotEditTeam implements EditTeamBehaviour{

	@Override
	public String editTeam() {
		System.out.println("I can not edit the team page");
		return "";
	}

}
