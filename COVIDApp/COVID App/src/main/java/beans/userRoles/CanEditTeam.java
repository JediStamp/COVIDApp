package beans.userRoles;

public class CanEditTeam implements EditTeamBehaviour {

	@Override
	public String editTeam() {
//		System.out.println("I can edit the team page");
		return "<a href=\"team.jsp\">TEAM</a>";
	}

}
