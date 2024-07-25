package cst8288.project.fwrp.service;

import java.util.Properties;

import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.PropertiesLoader;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;

public class EmailService {
	private Logger log = Logger.getLogger();

//	public static void main(String[] args) {
//		EmailService emailService = new EmailService();
//        emailService.send("koloyyee@gmail.com","Hello");
//	}
//	
	public void send(String to, String message) {
		// send email with JavaMail

		// Email related properties are loaded from application.properties
		Properties properties = PropertiesLoader.load();
//		String sender = properties.getProperty("email.sender");
//		String password = properties.getProperty("email.password");
		String sender = System.getenv("email.sender");
		String password = System.getenv("email.password");

		log.info("Sender: " + sender);
		log.info("Password: " + password);
		
//		Session session = Session.getInstance(properties, null);

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress( sender));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.setSubject("New Discounted Items!");
			msg.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");
			Transport.send(msg, sender, password);

			log.info("Sent message successfully");
		} catch (MessagingException e) {
			log.warn(e.getLocalizedMessage());
		}

	}
}
