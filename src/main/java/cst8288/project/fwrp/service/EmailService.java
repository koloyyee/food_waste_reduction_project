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

/**
 * EmailService class
 * <p>
 * reference:
 * https://mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/
 * </p>
 * <p>
 * This service is used when Retailer mark item as surplus performs a "pub/sub"
 * pattern to notify the Consumer
 * </p>
 */
public class EmailService {
	private Logger log = Logger.getLogger();

	public void send(String to, String message) {
		// send email with JavaMail

		// Email related properties are loaded from application.properties
		// set email properties from application.properties
		Properties properties = PropertiesLoader.load();
		String sender = properties.getProperty("email.sender");
		String password = properties.getProperty("email.password");

		/**
		 * <p>
		 * Email related properties are loaded from environment variables
		 * </p>
		 * <p>
		 * To set environment variables in Eclipse, go to Run -> Run Configurations ->
		 * (Tomcat) Environment -> Add
		 * </p>
		 * 
		 */
//		String sender = System.getenv("email.sender");
//		String password = System.getenv("email.password");

		// Set email properties programmatically.
//		Properties prop = new Properties();
//		prop.put("mail.smtp.host", "smtp.gmail.com");
//		prop.put("mail.smtp.port", "587");
//		prop.put("mail.smtp.auth", "true");
//		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sender));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.setSubject("New Discounted Items!");
			msg.setText("Dear Mail Crawler," + "\n\n Please do not spam my email!");
			Transport.send(msg, sender, password);

			log.info("Sent message successfully");
		} catch (MessagingException e) {
			log.warn(e.getLocalizedMessage());
		}

	}
}
