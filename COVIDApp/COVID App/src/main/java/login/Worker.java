package login;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import app.Email;

public class Worker implements Runnable {
	
	private Email email;
	
	public Worker(Email email) {
		this.email = email;
	}
	
	@Override
	public void run() {	    
	        try {	    			
	    		//set properties for our email provider
	    		Properties pr = new Properties();
	    		pr.setProperty("mail.smtp.host", "smtp.gmail.com");
	    		pr.setProperty("mail.smtp.port", "587");
	    		pr.setProperty("mail.smtp.auth", "true");
	    		pr.setProperty("mail.smtp.starttls.enable", "true");
	    		pr.put("mail.smtp.socketFactory.port", "465");
	    		pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    			
	    		//get session
	    		Session session = Session.getInstance(pr, new Authenticator(){
	    			@Override
	    			protected PasswordAuthentication getPasswordAuthentication() {
	    				return new PasswordAuthentication(email.getFromEmail(), email.getPassword());
	    			}
	    		});
	    			
	    		//set email message details
	            Message mess = new MimeMessage(session);
	                
	            mess.setFrom(new InternetAddress (email.getFromEmail()));
	            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getToEmail()));
	                
	            mess.setSubject("User Email Verification");
	            mess.setText("Registered Success please use this code: " + email.getVerCode());
	                
	            Transport.send(mess);
	                
	                
	        } catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    			
	}
}

