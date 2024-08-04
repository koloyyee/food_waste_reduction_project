package cst8288.project.fwrp.service;

/**
 * SmsService class represents a service to send SMS notifications.
 */
public class SmsService implements NotificationService{
	@Override
	public int send(String receiver, String title, String body) {
		// send sms
		System.out.printf("Sending SMS with twilio to %s\n", receiver);
		return 0;
	}
}
