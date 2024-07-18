package cst8288.project.fwrp.model;

public class Subscription {
	private User user;
	private Item item;
	private CommMethodType commMethodType;
	private String location; // city.

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

	public CommMethodType getCommMethodType() {
		return commMethodType;
	}

	public void setCommMethodType(CommMethodType commMethodType) {
		this.commMethodType = commMethodType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Subscription{" +
				"user=" + user +
				", item=" + item +
				", commMethodType=" + commMethodType +
				", location='" + location + '\'' +
				'}';
	}
}
