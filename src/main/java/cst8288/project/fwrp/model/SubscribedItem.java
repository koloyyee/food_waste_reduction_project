package cst8288.project.fwrp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cst8288.project.fwrp.dao.NotificationDao;

public class SubscribedItem implements Subject{
	private Item item;
	private List<Observer> subscribers = new ArrayList<>();
	private  NotificationDao notificationDao;

	public SubscribedItem(Item item) {
		this.item = item;
		this.notificationDao = new NotificationDao();
	}

	public void addSubscribers(List<Observer> subscribers) {
		this.subscribers.addAll(subscribers);
	}
	@Override
	public void addSubscribers(Observer subscribers) {
		this.subscribers.add(subscribers);
	}

	@Override
	public void removeSubscribers(Observer subscribers) {
		this.subscribers.remove(subscribers);
	}
	

	@Override
	public void notifySubscribers(String title, String body) throws SQLException {
		for (Observer subscriber : subscribers) {
			subscriber.update(title, body);
				// insert in notification table
//			    notificationDao.save(subscriber.getUser().getId(), item.getId(), subscriber.getUser().getCommMethod(), title + "\n" + body);
		}
		
	}

	@Override
	public String toString() {
		return "SubscribedItem{" +
				"item=" + item +
				", subscribers=" + subscribers +
				'}';
	}
}
