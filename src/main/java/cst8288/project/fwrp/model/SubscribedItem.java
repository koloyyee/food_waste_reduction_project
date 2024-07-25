package cst8288.project.fwrp.model;

import java.util.ArrayList;
import java.util.List;

public class SubscribedItem implements Subject{
	private Item item;
	private List<Observer> subscribers = new ArrayList<>();

	public SubscribedItem(Item item) {
		this.item = item;
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
	public void notifySubscribers(String title, String body) {
		for (Observer subscriber : subscribers) {
			subscriber.update(title, body);
		}
		
	}

}
