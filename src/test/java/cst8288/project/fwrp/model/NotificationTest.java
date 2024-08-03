package cst8288.project.fwrp.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


class NotificationTest {

    @SuppressWarnings("deprecation")
	@Test
    void testNotificationBuilder() {
        User user = new User();
        Item item = new Item();
        CommMethodType commMethod = CommMethodType.Email;
        
        LocalDateTime sentTime = LocalDateTime.now();
        String message = "Test message";

		Notification notification = new Notification.Builder()
                .setUser(user)
                .setItem(item)
                .setMessage(message)
                .setCommMethod(commMethod)
                .setSentTime(sentTime)
                .build();

        assertNotNull(notification);
        assertEquals(user, notification.getUser());
        assertEquals(item, notification.getItem());
        assertEquals(message, notification.getMessage());
        assertEquals(commMethod, notification.getCommMethod());
        assertEquals(sentTime, notification.getSentTime());
    }

    @SuppressWarnings("deprecation")
	@Test
    void testToString() {
        User user = new User();
        Item item = new Item();
        CommMethodType commMethod = CommMethodType.Email;
        
        LocalDateTime sentTime = LocalDateTime.now();
        String message = "Test message";

        Notification notification = new Notification.Builder()
                .setUser(user)
                .setItem(item)
                .setMessage(message)
                .setCommMethod(commMethod)
                .setSentTime(sentTime)
                .build();

        String expectedString = "Notification{" +
                "user=" + user +
                ", item=" + item +
                ", message='" + message + '\'' +
                ", commMethod=" + commMethod +
                ", sentTime=" + sentTime +
                '}';
        
        assertEquals(expectedString, notification.toString());
    }
}

