package questionnaires;

import java.sql.Timestamp;

public class Result {
	private String firstName;
	private String lastName;
	private Timestamp timeAnswered;
	private String goodToGo;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Timestamp getTimeAnswered() {
		return timeAnswered;
	}
	public void setTimeAnswered(Timestamp timeAnswered) {
		this.timeAnswered = timeAnswered;
	}
	public String getGoodToGo() {
		return goodToGo;
	}
	public void setGoodToGo(String goodToGo) {
		this.goodToGo = goodToGo;
	}
	
	
}
