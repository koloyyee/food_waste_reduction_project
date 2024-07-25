package cst8288.project.fwrp.model;

import cst8288.project.fwrp.service.EmailService;
import cst8288.project.fwrp.service.NotificationService;
import cst8288.project.fwrp.service.SmsService;

public class Subscription implements Observer{
	private User user;
	private Item item;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

//	@Override
//	public CommMethodType getCommMethodType() {
//		return user.getCommMethod();
//	}

	@Override
	public void update(String title, String body) {
		switch(user.getCommMethod()) {
		case Email:
			new EmailService().send(user.getEmail(), title, body);
			break;
		case Phone:
			new SmsService().send(user.getPhone(), title, body);
			break;
		}
	}
	@Override
	public String toString() {
		return "Subscription [user=" + user + ", item=" + item + "]";
	}

}
