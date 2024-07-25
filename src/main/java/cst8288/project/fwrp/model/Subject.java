package cst8288.project.fwrp.model;

public interface Subject {
	void addSubscribers(Observer observer);
	void removeSubscribers(Observer observer);
	void notifySubscribers(String title, String body);
}
