package cst8288.project.fwrp.model;

/**
 * Observer Pattern with Strategy Pattern
 *
 */
public interface Observer {
	void update(String title, String body); 
	User getUser();
}
