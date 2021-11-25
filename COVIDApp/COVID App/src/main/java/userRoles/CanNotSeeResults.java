package userRoles;

public class CanNotSeeResults implements SeeResultsBehaviour {

	@Override
	public String seeResults() {
//		System.out.println("I can not see the results page");
		return "";
	}

}
