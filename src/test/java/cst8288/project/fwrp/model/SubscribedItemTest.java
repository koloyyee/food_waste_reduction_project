package cst8288.project.fwrp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubscribedItemTest {

	@Test
	void shouldThrowNullAddSubscribers() {
		Item item = new Item();
		item.setName("Test Item");
		SubscribedItem subscribedItem = new SubscribedItem(item);
		assertThrows(NullPointerException.class, () -> subscribedItem.addSubscribers((Subscriber) null));
	}

	@Test
	void shouldThrowNullAddEmptyUser() {
		Item item = new Item();
		item.setName("Test Item");
		SubscribedItem subscribedItem = new SubscribedItem(item);
		assertThrows(IllegalArgumentException.class, () -> subscribedItem.addSubscribers(new Subscriber(new User())));
	}

	@Test
	void removeSubscribers() {
		Item item = new Item();
		item.setName("Test Item");
		SubscribedItem subscribedItem = new SubscribedItem(item);
		User user = new User();
		user.setId(1L);
		Subscriber subscriber = new Subscriber(user);
		subscribedItem.removeSubscribers(subscriber);
	}

	@Test
	void notifySubscribers() {
	}
}