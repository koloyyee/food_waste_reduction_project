package cst8288.project.fwrp.model;

import cst8288.project.fwrp.service.NotificationService;

/**
 * Observer Pattern with Strategy Pattern
 *  
 */
public interface Observer {
//	CommMethodType getCommMethodType();
	void update(String title, String body);
}
