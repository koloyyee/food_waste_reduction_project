package cst8288.project.fwrp.model;

import cst8288.project.fwrp.service.EmailService;
import cst8288.project.fwrp.service.NotificationService;
import cst8288.project.fwrp.service.SmsService;

import java.time.LocalDateTime;

public class Subscriber implements Observer{
	private User user;
	private LocalDateTime SubscribersDate;

	public Subscriber(User user) {
		this.user = user;
	}

	public Subscriber(User user, LocalDateTime SubscribersDate) {
		this.user = user;
		this.SubscribersDate = SubscribersDate;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getSubscribersDate() {return SubscribersDate;}

	public void setSubscribersDate(LocalDateTime SubscribersDate) {
		this.SubscribersDate = SubscribersDate;
	}

	@Override
	public void update(String title, String body) {
		switch(user.getCommMethod()) {
		case Email:
			new EmailService().send(user.getEmail(), title, body);
			break;
		case Phone:
			new SmsService().send(user.getPhone(), title, body);
			break;
		case Both:
			new EmailService().send(user.getEmail(), title, body);
			new SmsService().send(user.getPhone(), title, body);
			break;
		default:
			break;
		}
	}

	@Override
	public String toString() {
		return "Subscriber{" +
				"user=" + user +
				", SubscribersDate=" + SubscribersDate +
				'}';
	}
}
