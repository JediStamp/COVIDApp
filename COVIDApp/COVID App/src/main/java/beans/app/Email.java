package beans.app;

public class Email {
	
	String toEmail;
	String fromEmail = "apate0871@gmail.com";
	String password = "Patriots_87";
	String verCode;
	
	public Email(String toEmail, String verCode) {
		this.toEmail = toEmail;
		this.verCode = verCode;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerCode() {
		return verCode;
	}

	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}
}
