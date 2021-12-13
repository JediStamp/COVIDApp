package beans.userRoles;

public class CanSeeResults implements SeeResultsBehaviour {

	@Override
	public String seeResults() {
//		System.out.println("I can see the results page");
		return "<a href=\"results.jsp\">RESULTS</a>";
	}

}
