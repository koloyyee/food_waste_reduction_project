package cst8288.project.fwrp.model;

import java.sql.SQLException;
import java.util.*;

import cst8288.project.fwrp.dao.NotificationDao;
import org.jetbrains.annotations.NotNull;

/**
 * Subject - Item
 * Observer - User
 *
 * @see Subject
 * @see Observer
 * @see User
 * @see NotificationDao
 */
public class SubscribedItem implements Subject {
	private Item item;
	private Set<Observer> subscribers = new HashSet<>();
	private NotificationDao notificationDao;

	public SubscribedItem(Item item) {
		this.item = item;
		this.notificationDao = new NotificationDao();
	}

	public void addSubscribers(List<Observer> subscribers) {
		assert subscribers != null;
		Objects.requireNonNull(subscribers, "Subscriber cannot be null");
		this.subscribers.addAll(subscribers);
	}

	@Override
	public void addSubscribers(Observer subscribers) {
		assert subscribers != null;
		Objects.requireNonNull(subscribers, "Subscriber cannot be null");
		if (subscribers.getUser() == null || subscribers.getUser().getId() == null || subscribers.getUser().getEmail() == null)
			throw new IllegalArgumentException("Subscriber user cannot be an empty user object");
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
//			    notificationDao.save(subscriber.getUser().getId(), item.getId(), subscriber.getUser().getCommMethod(),
//			    title + "\n" + body);
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
