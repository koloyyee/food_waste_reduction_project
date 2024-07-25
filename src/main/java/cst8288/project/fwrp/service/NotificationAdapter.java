package cst8288.project.fwrp.service;

/**
 * Adapter Pattern
 * 
 * */
public class NotificationAdapter extends EmailService {
	private SmsService smsService;

	public NotificationAdapter(SmsService smsService) {
		this.smsService = smsService;
	}


}
