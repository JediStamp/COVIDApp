package login;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.activation.*;

import app.User;

public class SendEmail {

	public boolean sendEmail(User user) {
		boolean test = false;
		
		String toEmail = user.getEmail();
		String fromEmail = "stamplecoskie@gmail.com";
		String password = "J3d1St4mp";
		
		try {
			
			//set properties for our email provider
			Properties pr = new Properties();
			pr.setProperty("mail.smpt.host", "smpt.gmail.com");
			pr.setProperty("mail.smpt.port", "587");
			pr.setProperty("mail.smpt.auth", "true");
			pr.setProperty("mail.smpt.starttls.enable", "true");
			pr.put("mail.smtp.socketFactory.port", "587");
			pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			
			//get session
			Session session = Session.getInstance(pr, new Authenticator(){
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});
			
			//set email message details
            Message mess = new MimeMessage(session);
            
            mess.setFrom(new InternetAddress (fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            
            mess.setSubject("User Email Verification");
            mess.setText("Registered Success please use this code: " + user.getVerCode());
            
            Transport.send(mess);
            
            test = true;
            
            
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return test;
	}
	

}
