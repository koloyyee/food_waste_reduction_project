package cst8288.project.fwrp.model;

import cst8288.project.fwrp.service.EmailService;
import cst8288.project.fwrp.service.NotificationService;
import cst8288.project.fwrp.service.SmsService;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Subscriber
 * This is will be Observer in pub/sub pattern
 * This class is responsible for sending notifications to the user
 * when the publisher publishes a new message
 *
 * @see Observer
 * @see User
 * @see NotificationService
 * @see EmailService
 * @see SmsService
 */
public class Subscriber implements Observer {
	private User user;
	private LocalDateTime SubscribersDate;

	public Subscriber(User user) {
		Objects.requireNonNull(user, "User cannot be null");
		this.user = user;
	}

	public Subscriber(User user, LocalDateTime SubscribersDate) {
		Objects.requireNonNull(user, "User cannot be null");
		this.user = user;
		this.SubscribersDate = SubscribersDate;
	}

	@Override
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		Objects.requireNonNull(user, "User cannot be null");
		if (user.getId() == null || user.getEmail() == null || user.getPhone() == null)
			throw new IllegalArgumentException("User cannot be empty");
		this.user = user;
	}

	public LocalDateTime getSubscribersDate() {return SubscribersDate;}

	public void setSubscribersDate(LocalDateTime SubscribersDate) {
		this.SubscribersDate = SubscribersDate;
	}

	@Override
	public void update(String title, String body) {
		assert user != null;
		assert title != null;
		assert body != null;

		switch (user.getCommMethod()) {
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
