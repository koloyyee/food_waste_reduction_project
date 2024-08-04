package cst8288.project.fwrp.service;

/**
 * NotificationService interface represents a service to send notifications.
 */
public interface NotificationService {
	int send(String receiver, String title, String body);
}
